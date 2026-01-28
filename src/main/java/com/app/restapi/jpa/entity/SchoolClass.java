package com.app.restapi.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
}
