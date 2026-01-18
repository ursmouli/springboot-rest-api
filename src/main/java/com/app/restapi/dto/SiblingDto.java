package com.app.restapi.dto;

import java.time.LocalDate;

public class SiblingDto {

    private Long id;
    private String name;
    private LocalDate dob;
    private String institutionName;
    private String institutionPlace;

    public Long getId() {
        return id;
    }

    public SiblingDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SiblingDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public SiblingDto setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public SiblingDto setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
        return this;
    }

    public String getInstitutionPlace() {
        return institutionPlace;
    }

    public SiblingDto setInstitutionPlace(String institutionPlace) {
        this.institutionPlace = institutionPlace;
        return this;
    }
}
