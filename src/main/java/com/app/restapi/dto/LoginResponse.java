package com.app.restapi.dto;

import java.util.List;

public class LoginResponse {
	private String name;
	private String email;
	private String token;
	private long expiresIn;
	private List<String> roles;

	public String getName() {
		return name;
	}

	public LoginResponse setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public LoginResponse setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getToken() {
		return token;
	}

	public LoginResponse setToken(String token) {
		this.token = token;
		return this;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public LoginResponse setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public List<String> getRoles() {
		return roles;
	}

	public LoginResponse setRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}

}
