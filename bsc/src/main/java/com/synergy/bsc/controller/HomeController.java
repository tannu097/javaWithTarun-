package com.synergy.bsc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.synergy.bsc.helper.PasswordAES256Encryption;
import com.synergy.bsc.model.LoginRequest;
import com.synergy.bsc.model.LoginResponse;
import com.synergy.bsc.model.LogoutRequest;
import com.synergy.bsc.model.LogoutResponse;

@RestController
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	PasswordAES256Encryption passwordencryption;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest login, HttpServletRequest httpServletRequest) throws JSONException, Exception {
		
		final String loginUrl = "https://uat.bseindia.in/BEFS_Service/BEFS.svc/login";
		LoginResponse loginResponse = new LoginResponse();
		HttpStatus httpStatus = null;
		Gson gson = new Gson();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		LoginRequest loginRequest;
		try 
		{
			
		loginRequest=passwordencryption.getLoginDetails();
		// Creating JSON request body
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("memberCode", loginRequest.getMemberCode());
		jsonObject.put("loginId", loginRequest.getLoginId());
		jsonObject.put("password", loginRequest.getPassword());
		logger.info("Creating JSON body before sending request for Login API  = {}", jsonObject);

		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
		
			// Sending request
			logger.info("Sending Request to Login API");
			response = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, String.class);
		} catch (Exception exception) {
			logger.info("Error While Sending Request to Login API  :-> = {} ", exception.getMessage());
			loginResponse.setMessage("Something went wrong, Please contact Administration");
			return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		
		if (response.getStatusCode().is2xxSuccessful()) 
		{
			HttpSession httpSession=httpServletRequest.getSession(true);
			httpSession.setAttribute("loginId", loginRequest.getLoginId());
			logger.info("Successful Response Received from Login API = {}", response.getBody());
			
			//Converting JSON String into Object
			loginResponse = gson.fromJson(response.getBody(), LoginResponse.class);
			httpStatus=response.getStatusCode();
		}else if(response.getStatusCode().is4xxClientError()) 
		{
			loginResponse = gson.fromJson(response.getBody(), LoginResponse.class);
			httpStatus=response.getStatusCode();
		}

		return new ResponseEntity<LoginResponse>(loginResponse, httpStatus);
	}

	@PostMapping("/logout")
	public ResponseEntity<LogoutResponse> userLogout(@RequestBody LogoutRequest logoutRequest, HttpSession httpSession) {
		LogoutResponse logoutResponse = new LogoutResponse();
		final String logoutUrl = "https://uat.bseindia.in/BEFS_Service/BEFS.svc/logout";

		// Creating JSON request body
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("memberCode", logoutRequest.getMemberCode());
		jsonObject.put("loginId", logoutRequest.getLoginId());
		logger.info("Creating JSON body before sending request for Logout API  = {}", jsonObject);

		// Set HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
		Gson gson = new Gson();

		try {
			// Sending Request
			logger.info("Sending Request to Logout API");
			response = restTemplate.exchange(logoutUrl, HttpMethod.POST, entity, String.class);
		} catch (Exception exception) {
			logger.info("Error While request to api :-> ", exception.getMessage());
			logoutResponse.setMessage("Internal Server Error, Please try again.");
			return new ResponseEntity<LogoutResponse>(logoutResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		   //Parsing Result
		if (response.getStatusCode().is2xxSuccessful()) {
			httpSession.invalidate();
			logger.info("Successful Response Received from Logout API = {}", response.getBody());
			logoutResponse=gson.fromJson(response.getBody(), LogoutResponse.class);
		}

		return new ResponseEntity<LogoutResponse>(logoutResponse, HttpStatus.CREATED);
	}
	
}
