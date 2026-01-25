package com.app.restapi.jpa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
	
	Optional<Student> findByRollNumber(String registrationNumber);
}
