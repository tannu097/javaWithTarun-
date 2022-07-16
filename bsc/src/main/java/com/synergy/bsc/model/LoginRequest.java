package com.synergy.bsc.model;


public class LoginRequest {

	public String memberCode;
	
	public String loginId;
	
	public String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [memberCode=" + memberCode + ", loginId=" + loginId + ", password=" + password + "]";
	}
	
	
}
