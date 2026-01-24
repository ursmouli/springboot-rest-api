package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Country> findByName(String name);
}
