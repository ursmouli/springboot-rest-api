package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // ISO code
    // format : "countryCode-code"
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public State setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public State setCode(String code) {
        this.code = code;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public State setCountry(Country country) {
        this.country = country;
        return this;
    }
}
