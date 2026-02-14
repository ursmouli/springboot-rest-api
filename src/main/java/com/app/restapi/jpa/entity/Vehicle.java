package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;
    private Integer capacity;
    private String driverName;

    @OneToOne(mappedBy = "vehicle")
    private Route route;

    public Long getId() {
        return id;
    }

    public Vehicle setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Vehicle setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Vehicle setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public Vehicle setRoute(Route route) {
        this.route = route;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public Vehicle setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }
}
