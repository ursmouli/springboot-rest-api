package com.app.restapi.dto;

import java.util.Date;
import java.util.Set;

public class RegisterUserDto {
	private String email;

	private String password;

	private String firstName;
	private String middleName;
	private String lastName;

	private Date dateOfBirth;
	
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

	public String getFirstName() {
		return firstName;
	}

	public RegisterUserDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public RegisterUserDto setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public RegisterUserDto setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<String> getRoles() {
		return roles;
	}
	
	public RegisterUserDto setRoles(Set<String> roles) {
		this.roles = roles;
		return this;
	}

}
