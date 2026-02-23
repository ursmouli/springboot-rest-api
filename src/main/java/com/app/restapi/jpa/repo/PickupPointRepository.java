package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
    // Find all stops for a route, sorted by their sequence in the journey
    List<PickupPoint> findByRouteIdOrderBySequenceOrderAsc(Long routeId);
}
