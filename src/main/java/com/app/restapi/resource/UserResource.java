package com.app.restapi.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserResource {
	
	@GetMapping("/welcome")
	// @PreAuthorize("hasRole('ROLE_USER')")
	@PreAuthorize("hasRole(T(com.app.restapi.dto.Roles).ROLE_USER.name())")
	public String getWelcomeMsg() {
		return "Welcome";
	}

	
	@GetMapping("/all")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("hasRole(T(com.app.restapi.dto.Roles).ROLE_ADMIN.name())")
	public String getInfo() {
		return "info";
	}
}
