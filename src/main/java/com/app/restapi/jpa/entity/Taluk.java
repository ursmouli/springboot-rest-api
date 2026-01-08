package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Taluk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    public Long getId() {
        return id;
    }

    public Taluk setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Taluk setName(String name) {
        this.name = name;
        return this;
    }

    public District getDistrict() {
        return district;
    }

    public Taluk setDistrict(District district) {
        this.district = district;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Taluk setStatus(String status) {
        this.status = status;
        return this;
    }
}
