package com.app.restapi.dto;

public class RouteDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double distance;

    private VehicleDto vehicle;

    public Long getId() {
        return id;
    }

    public RouteDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public RouteDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public RouteDto setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public VehicleDto getVehicle() {
        return vehicle;
    }

    public RouteDto setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
        return this;
    }
}
