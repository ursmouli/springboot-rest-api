package com.app.restapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.jpa.entity.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {

}
