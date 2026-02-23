package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Double distance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequenceOrder ASC")
    private List<PickupPoint> pickupPoints = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Route setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Route setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Route setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Route setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Route setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Route setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public List<PickupPoint> getPickupPoints() {
        return pickupPoints;
    }

    public Route setPickupPoints(List<PickupPoint> pickupPoints) {
        this.pickupPoints = pickupPoints;
        return this;
    }
}
