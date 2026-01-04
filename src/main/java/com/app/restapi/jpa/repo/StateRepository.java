package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryId(Long countryId);

    boolean existsByNameIgnoreCaseAndCountryId(String name, Long countryId);
}
