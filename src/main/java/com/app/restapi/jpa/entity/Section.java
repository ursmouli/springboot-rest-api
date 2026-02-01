package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
@IdClass(SectionId.class)
public class Section {

    private String name;

    @Id
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @Id
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Employee classTeacher;

    public String getName() {
        return name;
    }

    public Section setName(String name) {
        this.name = name;
        return this;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Section setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public Employee getClassTeacher() {
        return classTeacher;
    }

    public Section setClassTeacher(Employee classTeacher) {
        this.classTeacher = classTeacher;
        return this;
    }
}
