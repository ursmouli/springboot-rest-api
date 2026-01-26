package com.app.restapi.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Column( nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String employeeNumber;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Guardian> guardians = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sibling> siblings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id", referencedColumnName = "id")
    private Address permanentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residential_address_id", referencedColumnName = "id")
    private Address residentialAddress;

    private boolean sameAsPermanentAddress;

//    @Enumerated(EnumType.STRING)
    private String role;

    private String maritalStatus;

    private String previousEmployment;

    private String email;

    // Many employees belong to one department
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @PrePersist
    public void generateEmployeeNumber() {
        // Example logic: REG-2026-RANDOM
        if (this.employeeNumber == null) {
            this.employeeNumber = "EMP-" +
                    java.time.Year.now().getValue() + "-" +
                    java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Employee setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Employee setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public Employee setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        return this;
    }

    public List<Guardian> getGuardians() {
        return guardians;
    }

    public Employee setGuardians(List<Guardian> guardians) {
        this.guardians = guardians;
        return this;
    }

    public List<Sibling> getSiblings() {
        return siblings;
    }

    public Employee setSiblings(List<Sibling> siblings) {
        this.siblings = siblings;
        return this;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public Employee setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
        return this;
    }

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public Employee setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
        return this;
    }

    public boolean isSameAsPermanentAddress() {
        return sameAsPermanentAddress;
    }

    public Employee setSameAsPermanentAddress(boolean sameAsPermanentAddress) {
        this.sameAsPermanentAddress = sameAsPermanentAddress;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Employee setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Employee setRole(String role) {
        this.role = role;
        return this;
    }

    public String getMartialStatus() {
        return maritalStatus;
    }

    public Employee setMartialStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public String getPreviousEmployment() {
        return previousEmployment;
    }

    public Employee setPreviousEmployment(String previousEmployment) {
        this.previousEmployment = previousEmployment;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }
}
