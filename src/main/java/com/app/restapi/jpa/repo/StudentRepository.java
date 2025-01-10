package com.app.restapi.jpa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findByRegistrationNumber(String registrationNumber);
}
