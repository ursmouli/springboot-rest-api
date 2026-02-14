package com.app.restapi.dto;

import com.app.restapi.jpa.entity.SectionId;

import java.util.ArrayList;
import java.util.List;

public class SectionDto {

    private SectionId id;
    private String name;
    private EmployeeDto classTeacher;
    private SchoolClassDto schoolClass;
    private List<SubjectDto> subjects = new ArrayList<>();
    private List<SectionSubjectDto> sectionSubjects = new ArrayList<>();

    public SectionId getId() {
        return id;
    }

    public SectionDto setId(SectionId id) {
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

    public List<SectionSubjectDto> getSectionSubjects() {
        return sectionSubjects;
    }

    public SectionDto setSectionSubjects(List<SectionSubjectDto> sectionSubjects) {
        this.sectionSubjects = sectionSubjects;
        return this;
    }
}
