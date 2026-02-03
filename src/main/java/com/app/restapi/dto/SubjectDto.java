package com.app.restapi.dto;

public class SubjectDto {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public SubjectDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubjectDto setName(String name) {
        this.name = name;
        return this;
    }
}
