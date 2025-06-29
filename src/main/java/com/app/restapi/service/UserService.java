package com.app.restapi.service;

import com.app.restapi.converter.ContactDetailsConverter;
import com.app.restapi.dto.ContactDetailsDto;
import com.app.restapi.jpa.entity.ContactDetails;
import com.app.restapi.jpa.repo.ContactDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ContactDetailsConverter contactDetailsConverter;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

}
