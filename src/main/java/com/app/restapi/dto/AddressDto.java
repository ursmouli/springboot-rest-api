package com.app.restapi.dto;

public class AddressDto {
    private Long id;

    private String houseNumber;
    private String street;
    private String landMark;

    private String place;
    private String postalCode;
    private String addressLine1;

    private Long countryId;
    private Long stateId;
    private Long districtId;
    private Long talukId;

    public AddressDto() {}
    public AddressDto(Long id, String street, String place, Long districtId, Long stateId, String postalCode, Long countryId) {
        this.id = id;
        this.street = street;
        this.place = place;
        this.districtId = districtId;
        this.stateId = stateId;
        this.postalCode = postalCode;
        this.countryId = countryId;
    }

    public Long getId() {
        return id;
    }

    public AddressDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public AddressDto setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getLandMark() {
        return landMark;
    }

    public AddressDto setLandMark(String landMark) {
        this.landMark = landMark;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public AddressDto setPlace(String city) {
        this.place = city;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public AddressDto setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getStateId() {
        return stateId;
    }

    public AddressDto setStateId(Long stateId) {
        this.stateId = stateId;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Long getCountryId() {
        return countryId;
    }

    public AddressDto setCountryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public AddressDto setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public Long getTalukId() {
        return talukId;
    }

    public AddressDto setTalukId(Long talukId) {
        this.talukId = talukId;
        return this;
    }
}
