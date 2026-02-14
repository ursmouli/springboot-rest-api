package com.app.restapi.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String description;

    // One department has many employees
    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Department setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Department setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Department setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Department setEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Department setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }
}
