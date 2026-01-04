package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String street;

	private String city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taluk_id", nullable = false)
	private Taluk taluk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	private String postalCode;

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

	public Taluk getTaluk() {
		return taluk;
	}

	public Address setTaluk(Taluk taluk) {
		this.taluk = taluk;
		return this;
	}

	public District getDistrict() {
		return district;
	}

	public Address setDistrict(District district) {
		this.district = district;
		return this;
	}

	public State getState() {
		return state;
	}

	public Address setState(State state) {
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

	public Country getCountry() {
		return country;
	}

	public Address setCountry(Country country) {
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
