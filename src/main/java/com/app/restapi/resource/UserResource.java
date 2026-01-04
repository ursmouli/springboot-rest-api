package com.app.restapi.resource;

import com.app.restapi.dto.ContactDetailsDto;
import com.app.restapi.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

	private final UserService userService;

	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
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
