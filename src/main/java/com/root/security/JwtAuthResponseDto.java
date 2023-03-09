package com.root.security;

public class JwtAuthResponseDto {

	private String accessToken;
	private String tokenTipe = "Bearer";

	public JwtAuthResponseDto(String accessToken, String tokenTipe) {
		super();
		this.accessToken = accessToken;
		this.tokenTipe = tokenTipe;
	}

	public JwtAuthResponseDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenTipe() {
		return tokenTipe;
	}

	public void setTokenTipe(String tokenTipe) {
		this.tokenTipe = tokenTipe;
	}

}
