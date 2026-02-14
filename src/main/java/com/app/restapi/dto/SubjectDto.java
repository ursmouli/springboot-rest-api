package com.app.restapi.dto;

public class SubjectDto {

    private Long id;
    private String name;
    private String code;
    private String description;
    private int credits;
    private DepartmentDto department;

    public Long getId() {
        return id;
    }

    public SubjectDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubjectDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public SubjectDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubjectDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCredits() {
        return credits;
    }

    public SubjectDto setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public SubjectDto setDepartment(DepartmentDto department) {
        this.department = department;
        return this;
    }
}
