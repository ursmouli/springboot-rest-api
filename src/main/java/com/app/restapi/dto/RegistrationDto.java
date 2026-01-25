package com.app.restapi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class RegistrationDto<T, S extends RegistrationDto<T, S>> {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String bloodGroup;
    private LocalDate dob;

    private AddressDto permanentAddress;
    private AddressDto residentialAddress;
    private List<GuardianDto> guardians = new ArrayList<>();
    private List<SiblingDto> siblings = new ArrayList<>();
    private boolean sameAsPermanentAddress;
    private String gender;



    // The "Self" cast method
    @SuppressWarnings("unchecked")
    protected S self() {
        return (S) this;
    }

    // This holds our "Specialized" object (StudentInfo or EmployeeInfo)
//    private T details;
//    public T getDetails() { return details; }
//    public void setDetails(T details) { this.details = details; }

    public Long getId() {
        return id;
    }

    public S setId(Long id) {
        this.id = id;
        return self();
    }

    public String getFirstName() {
        return firstName;
    }

    public S setFirstName(String firstName) {
        this.firstName = firstName;
        return self();
    }

    public String getMiddleName() {
        return middleName;
    }

    public S setMiddleName(String middleName) {
        this.middleName = middleName;
        return self();
    }

    public String getLastName() {
        return lastName;
    }

    public S setLastName(String lastName) {
        this.lastName = lastName;
        return self();
    }

    public LocalDate getDob() {
        return dob;
    }

    public S setDob(LocalDate dob) {
        this.dob = dob;
        return self();
    }

    public AddressDto getPermanentAddress() {
        return permanentAddress;
    }

    public S setPermanentAddress(AddressDto permanentAddress) {
        this.permanentAddress = permanentAddress;
        return self();
    }

    public AddressDto getResidentialAddress() {
        return residentialAddress;
    }

    public S setResidentialAddress(AddressDto residentialAddress) {
        this.residentialAddress = residentialAddress;
        return self();
    }

    public boolean isSameAsPermanentAddress() {
        return sameAsPermanentAddress;
    }

    public S setSameAsPermanentAddress(boolean sameAsPermanentAddress) {
        this.sameAsPermanentAddress = sameAsPermanentAddress;
        return self();
    }
    public List<GuardianDto> getGuardians() {
        return guardians;
    }

    public S setGuardians(List<GuardianDto> guardians) {
        this.guardians = guardians;
        return self();
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public S setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return self();
    }

    public List<SiblingDto> getSiblings() {
        return siblings;
    }

    public S setSiblings(List<SiblingDto> siblings) {
        this.siblings = siblings;
        return self();
    }

    public String getGender() {
        return gender;
    }

    public S setGender(String gender) {
        this.gender = gender;
        return self();
    }
}
