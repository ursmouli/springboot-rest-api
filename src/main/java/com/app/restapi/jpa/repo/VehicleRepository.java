package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByRegistrationNumberEqualsIgnoreCase(String registrationNumber);

    @Query("SELECT v FROM Vehicle v WHERE v.route IS NULL")
    List<Vehicle> findAvailableVehicles();
}
