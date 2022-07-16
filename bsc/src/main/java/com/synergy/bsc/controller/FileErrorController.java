package com.synergy.bsc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.synergy.bsc.model.UploadFileStatus;

/*
 * This controller is used to download error file 
 */

@RestController
public class FileErrorController {
	
	@GetMapping("/downloadHSErrorFile")
	public ResponseEntity<UploadFileStatus> downloadHSErrorFile(){
		UploadFileStatus fileStatus=new UploadFileStatus();
		
		return new ResponseEntity<UploadFileStatus>(fileStatus,HttpStatus.ACCEPTED);
	}

}
