package com.app.restapi.dto;

public class DepartmentDto {
    private Long id;
    private String name;
    private String code;
    private String description;

    public Long getId() {
        return id;
    }

    public DepartmentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DepartmentDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DepartmentDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
