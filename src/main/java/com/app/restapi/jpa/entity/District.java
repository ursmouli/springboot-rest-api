package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // format : "stateCode-code"
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public District setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public District setCode(String code) {
        this.code = code;
        return this;
    }

    public State getState() {
        return state;
    }

    public District setState(State state) {
        this.state = state;
        return this;
    }
}
