package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String houseNumber;
	private String street;
	private String landMark;

	private String place;
	private String postalCode;
	private String addressLine1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taluk_id", nullable = false)
	private Taluk taluk;

	@OneToOne(mappedBy = "permanentAddress", cascade = CascadeType.ALL)
	private Student studentPermanent;

	@OneToOne(mappedBy = "residentialAddress", cascade = CascadeType.ALL)
	private Student studentResidential;

	@OneToOne(mappedBy = "permanentAddress", cascade = CascadeType.ALL)
	private Employee employeePermanent;

	@OneToOne(mappedBy = "residentialAddress", cascade = CascadeType.ALL)
	private Employee employeeResidential;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private ContactDetails contactDetails;

	public Long getId() {
		return id;
	}

	public Address setId(Long id) {
		this.id = id;
		return this;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public Address setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public Address setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getPlace() {
		return place;
	}

	public Address setPlace(String place) {
		this.place = place;
		return this;
	}

	public String getLandMark() {
		return landMark;
	}

	public Address setLandMark(String landMark) {
		this.landMark = landMark;
		return this;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public Address setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
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

	public Employee getEmployeePermanent() {
		return employeePermanent;
	}

	public Address setEmployeePermanent(Employee employeePermanent) {
		this.employeePermanent = employeePermanent;
		return this;
	}

	public Employee getEmployeeResidential() {
		return employeeResidential;
	}

	public Address setEmployeeResidential(Employee employeeResidential) {
		this.employeeResidential = employeeResidential;
		return this;
	}
}
