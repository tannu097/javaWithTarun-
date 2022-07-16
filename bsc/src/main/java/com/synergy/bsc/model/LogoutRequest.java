package com.synergy.bsc.model;

public class LogoutRequest {

	public String loginId;
	
	public String memberCode;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	@Override
	public String toString() {
		return "LogoutRequest [loginId=" + loginId + ", memberCode=" + memberCode + "]";
	}
	
	
}
