package com.app.restapi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.restapi.jpa.entity.ContactDetails;

import java.util.Optional;


@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

	Optional<ContactDetails> findByEmail(String email);
}
