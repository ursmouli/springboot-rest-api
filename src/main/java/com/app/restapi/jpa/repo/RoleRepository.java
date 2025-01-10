package com.app.restapi.jpa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.jpa.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String role);

}
