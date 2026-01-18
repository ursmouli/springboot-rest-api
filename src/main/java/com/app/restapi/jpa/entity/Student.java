package com.app.restapi.jpa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = true)
	private String middleName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private LocalDate dob;
	
	@Column(nullable = false, unique = true)
	private String registrationNumber;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Guardian> guardians = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sibling> siblings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id", referencedColumnName = "id")
    private Address permanentAddress;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residential_address_id", referencedColumnName = "id")
    private Address residentialAddress;
    
    private boolean sameAsPermanentAddress;

	@PrePersist
	public void generateRegistrationNumber() {
		// Example logic: REG-2026-RANDOM
		if (this.registrationNumber == null) {
			this.registrationNumber = "STD-" +
					java.time.Year.now().getValue() + "-" +
					java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		}
	}

	public Long getId() {
		return id;
	}

	public Student setId(Long id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Student setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public Student setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Student setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public LocalDate getDob() {
		return dob;
	}

	public Student setDob(LocalDate dob) {
		this.dob = dob;
		return this;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Student setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
		return this;
	}

	public List<Guardian> getGuardians() {
        return guardians;
    }

    public Student setGuardians(List<Guardian> guardians) {
        this.guardians = guardians;
		return this;
    }

	public List<Sibling> getSiblings() {
		return siblings;
	}

	public Student setSiblings(List<Sibling> siblings) {
		this.siblings = siblings;
		return this;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public Student setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
		return this;
	}

	public Address getResidentialAddress() {
		return residentialAddress;
	}

	public Student setResidentialAddress(Address residentialAddress) {
		this.residentialAddress = residentialAddress;
		return this;
	}

	public boolean isSameAsPermanentAddress() {
		return sameAsPermanentAddress;
	}

	public Student setSameAsPermanentAddress(boolean sameAsPermanentAddress) {
		this.sameAsPermanentAddress = sameAsPermanentAddress;
		return this;
	}
	
}
