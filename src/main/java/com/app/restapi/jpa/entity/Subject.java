package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String description;

    private int credits;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

//    @ManyToMany(mappedBy = "subjects")
//    private Set<Section> sections = new HashSet<>();
//
//    @ManyToMany(mappedBy = "subjects")
//    private Set<Employee> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Subject setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Subject setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCredits() {
        return credits;
    }

    public Subject setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Subject setDepartment(Department department) {
        this.department = department;
        return this;
    }

    //    public Set<Section> getSections() {
//        return sections;
//    }
//
//    public Subject setSections(Set<Section> sections) {
//        this.sections = sections;
//        return this;
//    }
}
