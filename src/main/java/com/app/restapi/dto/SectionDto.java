package com.app.restapi.dto;

import java.util.List;

public class SectionDto {

    private Long id;
    private String name;
    private EmployeeDto classTeacher;
    private SchoolClassDto schoolClass;
    private List<SubjectDto> subjects;

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

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public SectionDto setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
        return this;
    }
}
