package com.app.restapi.dto;

import com.app.restapi.model.Relation;

public class GuardianDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Relation relation;

    public GuardianDto() {}
    public GuardianDto(Long id, String name, String email, String phone, Relation relation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.relation = relation;
    }

    public Long getId() {
        return id;
    }

    public GuardianDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GuardianDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GuardianDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public GuardianDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Relation getRelation() {
        return relation;
    }

    public GuardianDto setRelation(Relation relation) {
        this.relation = relation;
        return this;
    }
}
