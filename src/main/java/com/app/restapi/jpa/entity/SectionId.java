package com.app.restapi.jpa.entity;

import java.io.Serializable;

public class SectionId implements Serializable {
    private Long schoolClass; // Must match the name in the Entity
    private Long classTeacher; // Must match the name in the Entity

    public SectionId(){}

    public Long getSchoolClass() {
        return schoolClass;
    }

    public SectionId setSchoolClass(Long schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public Long getClassTeacher() {
        return classTeacher;
    }

    public SectionId setClassTeacher(Long classTeacher) {
        this.classTeacher = classTeacher;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
