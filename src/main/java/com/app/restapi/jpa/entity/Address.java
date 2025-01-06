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

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Student getStudentPermanent() {
		return studentPermanent;
	}

	public void setStudentPermanent(Student studentPermanent) {
		this.studentPermanent = studentPermanent;
	}

	public Student getStudentResidential() {
		return studentResidential;
	}

	public void setStudentResidential(Student studentResidential) {
		this.studentResidential = studentResidential;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

}
