package com.app.restapi.resource;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.dto.request.RoleRequest;
import com.app.restapi.dto.response.RoleResponse;
import com.app.restapi.jpa.entity.Role;
import com.app.restapi.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleResource {

	@Autowired
	private RoleService roleService;
	
	@PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = roleService.createRole(roleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
	
	@GetMapping("/all")
	public ResponseEntity<Set<RoleResponse>> getAllRoles() {
		return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoles());
	}
}
