package com.other.app.Model;

public class AuthResponse {
	
	public String accessToken;
	public String refreshToken;
	public AuthResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	

}
