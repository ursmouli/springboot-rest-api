package com.app.restapi.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PickupPointDto {

    private Long id;
    private String stopName;
    private LocalTime expectedArrivalTime;
    private Integer sequenceOrder;

    private List<StudentDto> students = new ArrayList<>();
    private RouteDto route;

    public Long getId() {
        return id;
    }

    public PickupPointDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStopName() {
        return stopName;
    }

    public PickupPointDto setStopName(String stopName) {
        this.stopName = stopName;
        return this;
    }

    public LocalTime getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public PickupPointDto setExpectedArrivalTime(LocalTime expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
        return this;
    }

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public PickupPointDto setSequenceOrder(Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
        return this;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public PickupPointDto setStudents(List<StudentDto> students) {
        this.students = students;
        return this;
    }

    public RouteDto getRoute() {
        return route;
    }

    public PickupPointDto setRoute(RouteDto route) {
        this.route = route;
        return this;
    }
}
