package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByNameEqualsIgnoreCase(String departmentName);
}
