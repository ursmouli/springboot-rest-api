package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Employee classTeacher;

    public Long getId() {
        return id;
    }

    public Section setId(Long id) {
        this.id = id;
        return this;
    }

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
