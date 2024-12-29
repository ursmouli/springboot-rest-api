package com.app.restapi.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserResource {
	
	@GetMapping("/welcome")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getWelcomeMsg() {
		return "Welcome";
	}

	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getInfo() {
		return "info";
	}
}
