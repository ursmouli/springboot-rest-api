package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByStateId(Long stateId);

    boolean existsByNameIgnoreCaseAndStateId(String name, Long stateId);
}
