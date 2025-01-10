package com.app.restapi.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String street;

	private String city;

	private String district;

	private String state;

	private String postalCode;

	private String country;

	@OneToOne(mappedBy = "permanentAddress", cascade = CascadeType.ALL)
	private Student studentPermanent;

	@OneToOne(mappedBy = "residentialAddress", cascade = CascadeType.ALL)
	private Student studentResidential;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private ContactDetails contactDetails;

	public Long getId() {
		return id;
	}

	public Address setId(Long id) {
		this.id = id;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public Address setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public Address setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getState() {
		return state;
	}

	public Address setState(String state) {
		this.state = state;
		return this;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Address setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Address setCountry(String country) {
		this.country = country;
		return this;
	}

	public Student getStudentPermanent() {
		return studentPermanent;
	}

	public Address setStudentPermanent(Student studentPermanent) {
		this.studentPermanent = studentPermanent;
		return this;
	}

	public Student getStudentResidential() {
		return studentResidential;
	}

	public Address setStudentResidential(Student studentResidential) {
		this.studentResidential = studentResidential;
		return this;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public Address setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
		return this;
	}

}
