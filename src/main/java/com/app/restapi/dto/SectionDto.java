package com.app.restapi.dto;

public class SectionDto {

    private Long id;
    private String name;
    private EmployeeDto classTeacher;
    private SchoolClassDto schoolClass;

    public Long getId() {
        return id;
    }

    public SectionDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SectionDto setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeDto getClassTeacher() {
        return classTeacher;
    }

    public SectionDto setClassTeacher(EmployeeDto classTeacher) {
        this.classTeacher = classTeacher;
        return this;
    }

    public SchoolClassDto getSchoolClass() {
        return schoolClass;
    }

    public SectionDto setSchoolClass(SchoolClassDto schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }
}
