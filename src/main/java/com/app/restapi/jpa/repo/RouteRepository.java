package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {

}
