package com.app.restapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.restapi.dto.request.RoleRequest;
import com.app.restapi.dto.response.RoleResponse;
import com.app.restapi.jpa.dao.RoleRepository;
import com.app.restapi.jpa.entity.Role;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role createRole(RoleRequest roleRequest) {
		if (roleRepository.findByName(roleRequest.getName()).isPresent()) {
            throw new RuntimeException("Role already exists: " + roleRequest.getName());
        }

        Role role = new Role();
        
        if (StringUtils.hasText(roleRequest.getName()) && !roleRequest.getName().startsWith("ROLE_")) {
        	roleRequest.setName("ROLE_" + roleRequest.getName());
        }
        
        role.setName(roleRequest.getName());
        return roleRepository.save(role);
	}

	public Set<RoleResponse> getRoles() {
		List<Role> roles = roleRepository.findAll();
		
		Set<RoleResponse> roleResponse = new HashSet<>();
		for (Role role : roles) {
			roleResponse.add(new RoleResponse(role.getId(), role.getName()));
		}
		
		return roleResponse;
	}
}
