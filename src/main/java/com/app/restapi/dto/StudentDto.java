package com.app.restapi.dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String bloodGroup;
    private LocalDate dob;
    private String registrationNumber;
    private AddressDto permanentAddress;
    private AddressDto residentialAddress;
    private List<GuardianDto> guardians = new ArrayList<>();
    private List<SiblingDto> siblings = new ArrayList<>();
    private boolean sameAsPermanentAddress;
    private String gender;

    public StudentDto() {}
    public StudentDto(Long id, String firstName, String middleName, String lastName, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
    }
    public StudentDto(Long id, String firstName, String middleName, String lastName, LocalDate dob,
                      AddressDto permanentAddress, AddressDto residentialAddress, List<GuardianDto> guardians, boolean sameAsPermanentAddress) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.permanentAddress = permanentAddress;
        this.residentialAddress = residentialAddress;
        this.guardians = guardians;
        this.sameAsPermanentAddress = sameAsPermanentAddress;
    }

    public Long getId() {
        return id;
    }

    public StudentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public StudentDto setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public StudentDto setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public StudentDto setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public AddressDto getPermanentAddress() {
        return permanentAddress;
    }

    public StudentDto setPermanentAddress(AddressDto permanentAddress) {
        this.permanentAddress = permanentAddress;
        return this;
    }

    public AddressDto getResidentialAddress() {
        return residentialAddress;
    }

    public StudentDto setResidentialAddress(AddressDto residentialAddress) {
        this.residentialAddress = residentialAddress;
        return this;
    }

    public boolean isSameAsPermanentAddress() {
		return sameAsPermanentAddress;
	}
	public StudentDto setSameAsPermanentAddress(boolean sameAsPermanentAddress) {
		this.sameAsPermanentAddress = sameAsPermanentAddress;
		return this;
	}
	public List<GuardianDto> getGuardians() {
        return guardians;
    }

    public StudentDto setGuardians(List<GuardianDto> guardians) {
        this.guardians = guardians;
        return this;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public StudentDto setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public List<SiblingDto> getSiblings() {
        return siblings;
    }

    public StudentDto setSiblings(List<SiblingDto> siblings) {
        this.siblings = siblings;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public StudentDto setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
