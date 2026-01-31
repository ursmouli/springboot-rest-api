package com.app.restapi.dto;

public class SchoolClassDto {

    private Long id;
    private String name;
    private String academicYear;

    public Long getId() {
        return id;
    }

    public SchoolClassDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolClassDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public SchoolClassDto setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
        return this;
    }
}
