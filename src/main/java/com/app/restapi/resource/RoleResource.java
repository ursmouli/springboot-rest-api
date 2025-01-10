package com.app.restapi.resource;

import java.util.Set;

import com.app.restapi.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleResource {

	@Autowired
	private RoleService roleService;
	
	@PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
		RoleDto role = roleService.createRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
	
	@GetMapping("/all")
	public ResponseEntity<Set<RoleDto>> getAllRoles() {
		return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoles());
	}
}
