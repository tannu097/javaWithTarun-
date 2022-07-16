package com.synergy.bsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.synergy.bsc.helper.PasswordAES256Encryption;
import com.synergy.bsc.model.FileUploadResponse;

/*
 * This controller is used to upload all files 
 */



@RestController
public class FileUploadController {

	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	PasswordAES256Encryption passwordaes256encryption;
	
	Gson gson=new Gson();

	@PostMapping(value = "/uploadHoldingFile", 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<FileUploadResponse> uploadHoldingFile(
			@RequestParam("file") MultipartFile multipartFile)
	{
		final String uploadHoldingFileUrl = "https://uat.bseindia.in/BEFS_Service/BEFS.svc/HoldingFileUpload";
		FileUploadResponse holdingFileUploadResponse = new FileUploadResponse();
		HttpStatus httpStatus = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("MEMBERCODE", "1234");
		headers.add("LOGINID", "1234");
		headers.add("LASTWEEKDATE", "21-05-2022");
		headers.add("TOKEN", "0xd158c80be7ebf5280e9f7425925fd821");
		logger.info("creating header for uploadHoldingFile API ={}", headers);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", PasswordAES256Encryption.convert(multipartFile));

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
            logger.info("Sending uploadHoldingFile API :-");
			response = restTemplate.exchange(uploadHoldingFileUrl, HttpMethod.POST, requestEntity, String.class);
		} catch (Exception e) {
			logger.error("Error while sending uploadHoldingFile API = {}",e.getMessage());
			return new ResponseEntity<FileUploadResponse>(holdingFileUploadResponse,
					HttpStatus.INTERNAL_SERVER_ERROR);
	}

		gson = new Gson();
		if (response.getStatusCode().is2xxSuccessful()) {
			logger.info("Received Successful Response from uploadHoldingFile API = {}" ,response.getBody());
			holdingFileUploadResponse = gson.fromJson(response.getBody(), FileUploadResponse.class);
			httpStatus=response.getStatusCode();
		} else if (response.getStatusCode().is4xxClientError()) {
			holdingFileUploadResponse = gson.fromJson(response.getBody(), FileUploadResponse.class);
			httpStatus=response.getStatusCode();
		}

		return new ResponseEntity<FileUploadResponse>(holdingFileUploadResponse, httpStatus);
	}
	
	   @PostMapping(value = "/cashbalancefile", 
			   consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			   produces = MediaType.APPLICATION_JSON_VALUE)
	   
	    public ResponseEntity<FileUploadResponse> uploadCashBalanceFile( @RequestParam("file") MultipartFile multipartFile) 
	   {
		   
	        final String uploadCashHoldingfileuri = "https://uat.bseindia.in/BEFS_Service/BEFS.svc/CashBalanceFileUpload";
	        FileUploadResponse fileUploadResponse = new FileUploadResponse();
	        HttpStatus httpStatus = null;
	        gson = new Gson();
	        
	        // Setting Headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	        headers.add("MEMBERCODE", "1234");
	        headers.add("LOGINID", "1234");
	        headers.add("LASTWEEKDATE", "21-05-2022");
	        headers.add("TOKEN", "0xd158c80be7ebf5280e9f7425925fd821");
	        logger.info("creating header for cashBalanceUpload API ={}", headers);
	        // Add the given single value to the current list of values for the given key
	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	        body.add("file",  PasswordAES256Encryption.convert(multipartFile));
	        //Represents an HTTP request or response entity, consisting of headers and body. Often used in combination with the RestTemplate 

	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = null;
	        try {
	        	logger.info("Sending Request to cashbalancefile upload API >> ");
	            response = restTemplate.exchange(uploadCashHoldingfileuri, HttpMethod.POST, requestEntity, String.class);

	        } catch (Exception e) {
	        	logger.error("Error while sending cashbalancefile API = {}",e.getMessage());
	            return new ResponseEntity<FileUploadResponse>(fileUploadResponse,
	                    HttpStatus.INTERNAL_SERVER_ERROR);

	        }
	        
	        if (response.getStatusCode().is2xxSuccessful()) {
	            logger.info("Received Successful Response from cashBalanceUpload API = {}", response.getBody());
	            fileUploadResponse = gson.fromJson(response.getBody(), FileUploadResponse.class);
	            httpStatus=response.getStatusCode();
	        } else if (response.getStatusCode().is4xxClientError()) {
	        	fileUploadResponse = gson.fromJson(response.getBody(), FileUploadResponse.class);
	        	httpStatus=response.getStatusCode();
	        }
	        return new ResponseEntity<FileUploadResponse>(fileUploadResponse, httpStatus);

	    }
	
	
	   @PostMapping(value = "/bankbalancefileupload", 
			   consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
			   produces = MediaType.APPLICATION_JSON_VALUE)
	   
	    public ResponseEntity<FileUploadResponse> uploadBankBalanceFile(
	            @RequestParam("file") MultipartFile multipartFile) {

	        final String BankbalanceFileuri = "https://uat.bseindia.in/BEFS_Service/BEFS.svc/BankBalanceFileUpload";

	        FileUploadResponse bankbalanceFileResponce = new FileUploadResponse();

	        HttpStatus httpStatus = null;
	        gson = new Gson();
	        HttpHeaders headers = new HttpHeaders();

	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	        headers.add("MEMBERCODE", "1234");
	        headers.add("LOGINID", "1234");
	        headers.add("LASTWEEKDATE", "21-05-2022");
	        headers.add("TOKEN", "0xd158c80be7ebf5280e9f7425925fd821");
	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	        body.add("file", PasswordAES256Encryption.convert(multipartFile));

	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = null;
	        try {
	        	logger.info("Sending Request to bankbalancefileupload API >> ");
	            response = restTemplate.exchange(BankbalanceFileuri, HttpMethod.POST, requestEntity, String.class);

	        } catch (Exception e) {
	        	logger.error("Error while sending bankbalancefileupload API = {}",e.getMessage());
	            return new ResponseEntity<FileUploadResponse>(bankbalanceFileResponce,
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        
	        if (response.getStatusCode().is2xxSuccessful()) {
	            logger.info("Received Successful Response from bankbalancefileupload API = {}", response.getBody());
	            bankbalanceFileResponce = gson.fromJson(response.getBody(), FileUploadResponse.class);
	            httpStatus=response.getStatusCode();
	        } else if (response.getStatusCode().is4xxClientError()) {
	            bankbalanceFileResponce = gson.fromJson(response.getBody(), FileUploadResponse.class);
	            httpStatus=response.getStatusCode();
	        }
	        return new ResponseEntity<FileUploadResponse>(bankbalanceFileResponce, httpStatus);
	    }
	}
	
