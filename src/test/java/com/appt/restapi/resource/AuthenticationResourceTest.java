package com.appt.restapi.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.restapi.RestApiApplication;

@SpringBootTest
@ContextConfiguration(classes = { RestApiApplication.class })
@AutoConfigureMockMvc
public class AuthenticationResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/all")).andExpect(status().is4xxClientError());
    }
}
