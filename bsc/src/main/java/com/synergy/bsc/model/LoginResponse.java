package com.synergy.bsc.model;


public class LoginResponse {

	public String memberCode;
	
	public String loginId;
	
	public String token;
	
	public String errorCode;
	
	public String message;

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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
		return "LoginResponse [memberCode=" + memberCode + ", loginId=" + loginId + ", token=" + token + ", errorCode="
				+ errorCode + ", message=" + message + "]";
	}
	
	
	
}
