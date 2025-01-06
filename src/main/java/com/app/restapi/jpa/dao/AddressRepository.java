package com.app.restapi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.jpa.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
