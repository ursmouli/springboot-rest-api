package com.app.restapi.dto;

public class VehicleDto {
    private Long id;
    private String registrationNumber;
    private Integer capacity;
    private String driverName;

    public Long getId() {
        return id;
    }

    public VehicleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleDto setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public VehicleDto setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public VehicleDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }
}
