package com.synergy.bsc.model;

public class FileUploadResponse {
	
	public String errorCode;
	
	public String message;
	
	public String acknowledgeId;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAcknowledgeId() {
		return acknowledgeId;
	}

	public void setAcknowledgeId(String acknowledgeId) {
		this.acknowledgeId = acknowledgeId;
	}

	@Override
	public String toString() {
		return "HoldingFileUploadResponse [errorCode=" + errorCode + ", message=" + message + ", acknowledgeId="
				+ acknowledgeId + "]";
	}
	
	

}
