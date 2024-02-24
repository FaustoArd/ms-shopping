package com.lord.securityservice.dto;

public class LoginResponseDto {

	private String jwtToken;
	
	public LoginResponseDto(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	public String getJwtToken() {
		return this.jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
