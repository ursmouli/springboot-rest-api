package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Taluk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalukRepository extends JpaRepository<Taluk, Long> {
    List<Taluk> findByDistrictId(Long districtId);

    // Check if a taluk name exists anywhere in a specific state
    boolean existsByNameIgnoreCaseAndDistrictStateId(String name, Long stateId);
}
