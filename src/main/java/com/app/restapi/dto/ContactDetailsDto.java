package com.app.restapi.dto;

import java.util.Date;

public class ContactDetailsDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;

    public ContactDetailsDto() {}

    public String getFirstName() {
        return firstName;
    }

    public ContactDetailsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public ContactDetailsDto setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactDetailsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ContactDetailsDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public ContactDetailsDto setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
