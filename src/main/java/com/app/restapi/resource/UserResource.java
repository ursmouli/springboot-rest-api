package com.app.restapi.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserResource {

	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER')")
	public String getInfo() {
		return "info";
	}
}
