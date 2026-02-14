package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school_class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String academicYear;

//    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL)
//    private Set<Section> sections = new HashSet<>();

    // helper method
//    public void addSubject(Subject subject) {
//        this.subjects.add(subject);
//        subject.getSchoolClasses().add(this);
//    }

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

//    public Set<Section> getSections() {
//        return sections;
//    }
//
//    public SchoolClass setSections(Set<Section> sections) {
//        this.sections = sections;
//        return this;
//    }
}
