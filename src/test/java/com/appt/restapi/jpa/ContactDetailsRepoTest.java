package com.appt.restapi.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.app.restapi.RestApiApplication;
import com.app.restapi.jpa.dao.ContactDetailsRepository;
import com.app.restapi.jpa.entity.ContactDetails;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = RestApiApplication.class)
public class ContactDetailsRepoTest {

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	
	@Test
	public void testCreateContactDetails_success() {
		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setEmail("mail@mail.com");
		contactDetails.setPassword("password");
		contactDetails.setFullName("fullname");
		
		ContactDetails result = contactDetailsRepository.save(contactDetails);
		
		assertNotNull(result);
		assertEquals("mail@mail.com", result.getEmail());
	}
}
