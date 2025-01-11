package com.app.restapi.dto;

import java.util.Set;

public class RegisterUserDto {
	private String email;

	private String password;

	private String fullName;
	
	private Set<String> roles;

	public String getEmail() {
		return email;
	}

	public RegisterUserDto setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public RegisterUserDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public RegisterUserDto setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public Set<String> getRoles() {
		return roles;
	}
	
	public RegisterUserDto setRoles(Set<String> roles) {
		this.roles = roles;
		return this;
	}

}
