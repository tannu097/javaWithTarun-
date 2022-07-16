package com.synergy.bsc.model;

public class LogoutResponse {
	
	public String errorCode;
	
	public String message;

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

	@Override
	public String toString() {
		return "LogoutResponse [errorCode=" + errorCode + ", message=" + message + "]";
	}

	
	

}
