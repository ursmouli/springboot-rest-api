package com.app.restapi.jpa.specifications;

import com.app.restapi.jpa.entity.Route;
import com.app.restapi.jpa.entity.Vehicle;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class RouteSpecification {

    public static Specification<Route> hasRouteName(String name) {
        return (root, query, cb) ->
                name == null ? cb.conjunction() : cb.equal(root.get("name"), name);
    }

    public static Specification<Route> hasMinCapacity(Integer capacity) {
        return (root, query, cb) -> {
            if (capacity == null) return cb.conjunction();

            Join<Route, Vehicle> vehicleJoin = root.join("vehicles", JoinType.INNER);

            return cb.greaterThanOrEqualTo(vehicleJoin.get("capacity"), capacity);
        };
    }

    public static Specification<Route> hasDriver(String driverName) {
        return (root, query, cb) -> {
            if (driverName == null) return cb.conjunction();

            Join<Route, Vehicle> vehicleJoin = root.join("vehicle", JoinType.INNER);

            return cb.equal(vehicleJoin.get("driverName"), driverName);
        };
    }
}
