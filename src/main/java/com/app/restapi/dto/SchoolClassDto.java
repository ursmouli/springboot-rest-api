package com.app.restapi.dto;

public class SchoolClassDto {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public SchoolClassDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolClassDto setName(String name) {
        this.name = name;
        return this;
    }
}
