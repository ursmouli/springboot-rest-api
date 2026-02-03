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

//    public Set<Section> getSections() {
//        return sections;
//    }
//
//    public Subject setSections(Set<Section> sections) {
//        this.sections = sections;
//        return this;
//    }
}
