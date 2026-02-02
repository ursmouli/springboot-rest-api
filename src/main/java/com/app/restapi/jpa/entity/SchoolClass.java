package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String academicYear;

    @ManyToMany
    @JoinTable(
            name = "class_subjects",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    // helper method
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getSchoolClasses().add(this);
    }

    public Long getId() {
        return id;
    }

    public SchoolClass setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolClass setName(String name) {
        this.name = name;
        return this;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public SchoolClass setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public SchoolClass setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }
}
