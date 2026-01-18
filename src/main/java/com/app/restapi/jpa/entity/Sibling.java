package com.app.restapi.jpa.entity;

import com.app.restapi.model.Relation;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Sibling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Relation relation;

    private String institutionName;
    private String institutionPlace;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return id;
    }

    public Sibling setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sibling setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Sibling setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public Relation getRelation() {
        return relation;
    }

    public Sibling setRelation(Relation relation) {
        this.relation = relation;
        return this;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public Sibling setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
        return this;
    }

    public String getInstitutionPlace() {
        return institutionPlace;
    }

    public Sibling setInstitutionPlace(String institutionPlace) {
        this.institutionPlace = institutionPlace;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Sibling setStudent(Student student) {
        this.student = student;
        return this;
    }
}
