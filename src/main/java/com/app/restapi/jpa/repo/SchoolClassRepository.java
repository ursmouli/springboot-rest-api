package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}
