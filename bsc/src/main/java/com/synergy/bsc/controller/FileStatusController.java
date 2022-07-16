package com.synergy.bsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.synergy.bsc.model.UploadFileStatus;

/*
 * This controller is used to check status of upload files 
 */

@RestController
public class FileStatusController {

	private Logger logger = LoggerFactory.getLogger(FileStatusController.class);
	
	Gson gson=new Gson();
	
	@GetMapping("/statusHoldingFile")
	public ResponseEntity<UploadFileStatus> statusHoldingFile(@RequestParam("acknowledgementId") String  acknowledgementId){
		
		final String statusHoldingFileUrl="https://uat.bseindia.in/BEFS_Service/BEFS.svc/Query_HS_FileUpload/'"+acknowledgementId+"'";
		
		HttpStatus httpStatus = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("MEMBERCODE", "1234");
		headers.add("LOGINID", "1234");
		headers.add("LASTWEEKDATE", "21-05-2022");
		headers.add("TOKEN", "0x047da79310e34180984723218b0504f6");
		logger.info("creating header for statusHoldingFile API ={}", headers);
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		UploadFileStatus uploadFileStatus=new UploadFileStatus();
		ResponseEntity<String> response = null;
		gson=new Gson();
		
		try{
			// Sending request
			logger.info("Sending Request to statusHoldingFile API");
			response = restTemplate.exchange(statusHoldingFileUrl, HttpMethod.GET, requestEntity, String.class);
			
		} catch (Exception exception) {
			logger.info("Error While Sending Request to Login API  :-> = {} ", exception.getMessage());
			uploadFileStatus.setMessage(exception.getMessage());
			return new ResponseEntity<UploadFileStatus>(uploadFileStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (response.getStatusCode().is2xxSuccessful()) {
			logger.info("Received Successful Response from uploadHoldingFile API = {}" ,response.getBody());
			uploadFileStatus = gson.fromJson(response.getBody(), UploadFileStatus.class);
			httpStatus=response.getStatusCode();
		} else if (response.getStatusCode().is4xxClientError()) {
			uploadFileStatus = gson.fromJson(response.getBody(), UploadFileStatus.class);
			httpStatus=response.getStatusCode();
		}

		return new ResponseEntity<UploadFileStatus>(uploadFileStatus, httpStatus);
		
	}
	
	@GetMapping("/statusCBFileUpload")
	public ResponseEntity<UploadFileStatus> statusCBFileUpload(@RequestParam("acknowledgementId") String  acknowledgementId){
		
		final String statusCBFileUploadUrl=" https://uat.bseindia.in/BEFS_Service/BEFS.svc/Query_CB_FileUpload/'"+acknowledgementId+"'";
		
		HttpStatus httpStatus = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("MEMBERCODE", "1234");
		headers.add("LOGINID", "1234");
		headers.add("LASTWEEKDATE", "21-05-2022");
		headers.add("TOKEN", "0x047da79310e34180984723218b0504f6");
		logger.info("creating header for statusHoldingFile API ={}", headers);
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		UploadFileStatus statusCBFileUpload=new UploadFileStatus();
		ResponseEntity<String> response = null;
		gson=new Gson();
		
		try{
			// Sending request
			logger.info("Sending Request to statusCBFileUpload API");
			response = restTemplate.exchange(statusCBFileUploadUrl, HttpMethod.GET, requestEntity, String.class);
			
		} catch (Exception exception) {
			logger.info("Error While Sending Request to statusCBFileUpload API  :-> = {} ", exception.getMessage());
			statusCBFileUpload.setMessage(exception.getMessage());
			return new ResponseEntity<UploadFileStatus>(statusCBFileUpload, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (response.getStatusCode().is2xxSuccessful()) {
			logger.info("Received Successful Response from statusCBFileUpload API = {}" ,response.getBody());
			statusCBFileUpload = gson.fromJson(response.getBody(), UploadFileStatus.class);
			httpStatus=response.getStatusCode();
		} else if (response.getStatusCode().is4xxClientError()) {
			statusCBFileUpload = gson.fromJson(response.getBody(), UploadFileStatus.class);
			httpStatus=response.getStatusCode();
		}

		return new ResponseEntity<UploadFileStatus>(statusCBFileUpload, httpStatus);
		
	}
	
	@GetMapping("/statusBBFileUpload")
	public ResponseEntity<UploadFileStatus> statusBBFileUpload(@RequestParam("acknowledgementId") String  acknowledgementId){
		
		final String statusBBFileUploadUrl=" https://uat.bseindia.in/BEFS_Service/BEFS.svc/Query_BB_FileUpload/'"+acknowledgementId+"'";
		
		HttpStatus httpStatus = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("MEMBERCODE", "1234");
		headers.add("LOGINID", "1234");
		headers.add("LASTWEEKDATE", "21-05-2022");
		headers.add("TOKEN", "0x047da79310e34180984723218b0504f6");
		logger.info("creating header for statusBBFileUpload API ={}", headers);
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		UploadFileStatus statusBBFileUpload=new UploadFileStatus();
		ResponseEntity<String> response = null;
		gson=new Gson();
		
		try{
			// Sending request
			logger.info("Sending Request to statusBBFileUpload API");
			response = restTemplate.exchange(statusBBFileUploadUrl, HttpMethod.GET, requestEntity, String.class);
			
		} catch (Exception exception) {
			logger.info("Error While Sending Request to statusBBFileUpload API  :-> = {} ", exception.getMessage());
			statusBBFileUpload.setMessage(exception.getMessage());
			return new ResponseEntity<UploadFileStatus>(statusBBFileUpload, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (response.getStatusCode().is2xxSuccessful()) {
			logger.info("Received Successful Response from statusBBFileUpload API = {}" ,response.getBody());
			statusBBFileUpload = gson.fromJson(response.getBody(), UploadFileStatus.class);
			httpStatus=response.getStatusCode();
		} else if (response.getStatusCode().is4xxClientError()) {
			statusBBFileUpload = gson.fromJson(response.getBody(), UploadFileStatus.class);
			httpStatus=response.getStatusCode();
		}

		return new ResponseEntity<UploadFileStatus>(statusBBFileUpload, httpStatus);
		
	}
}

