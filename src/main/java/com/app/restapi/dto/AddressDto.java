package com.app.restapi.dto;

public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String district;
    private String state;
    private String postalCode;
    private String country;

    public AddressDto() {}
    public AddressDto(Long id, String street, String city, String district, String state, String postalCode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.district = district;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public AddressDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AddressDto setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
