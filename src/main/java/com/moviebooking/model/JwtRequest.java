package com.moviebooking.model;

public class JwtRequest {

	private String loginId;
	private String password;

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

	public JwtRequest(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}

	public JwtRequest() {
	}

	@Override
	public String toString() {
		return "JwtRequest [loginId=" + loginId + ", password=" + password + "]";
	}
}
