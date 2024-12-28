package com.appt.restapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.restapi.RestApiApplication;
import com.app.restapi.jpa.dao.ContactDetailsRepository;
import com.app.restapi.service.AuthenticationService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApiApplication.class)
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

	@Mock
	private ContactDetailsRepository contactDetailsRepository;
	
	
	@InjectMocks
	private AuthenticationService authenticationService;
	
	@Test
	public void test() {
		assertNotNull(contactDetailsRepository);
	}
}
