package com.app.restapi.jpa.repo;

import com.app.restapi.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmployeeNumber(String employeeNumber);

    // Custom query to find all teachers in a specific department
    List<Employee> findByDepartmentName(String deptName);


    List<Employee> findByRole(String role);

}
