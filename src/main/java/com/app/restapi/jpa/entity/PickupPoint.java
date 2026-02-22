package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pickup_point")
public class PickupPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stopName;
    private LocalTime expectedArrivalTime; // e.g., 07:15 AM
    private Integer sequenceOrder; // To keep stops in order (1st stop, 2nd stop, etc.)

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "pickupPoint")
    private List<Student> students = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public PickupPoint setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalTime getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public PickupPoint setExpectedArrivalTime(LocalTime expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
        return this;
    }

    public String getStopName() {
        return stopName;
    }

    public PickupPoint setStopName(String stopName) {
        this.stopName = stopName;
        return this;
    }

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public PickupPoint setSequenceOrder(Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public PickupPoint setRoute(Route route) {
        this.route = route;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public PickupPoint setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setPickupPoint(null); // Clear the back-reference
    }
}
