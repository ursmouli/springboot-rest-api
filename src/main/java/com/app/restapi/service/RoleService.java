package com.app.restapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.restapi.dto.RoleDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.restapi.jpa.repo.RoleRepository;
import com.app.restapi.jpa.entity.Role;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public RoleDto createRole(RoleDto roleDto) {
		if (roleRepository.findByName(roleDto.getName()).isPresent()) {
            throw new RuntimeException("Role already exists: " + roleDto.getName());
        }

        Role role = new Role();
        
        if (StringUtils.hasText(roleDto.getName()) && !roleDto.getName().startsWith("ROLE_")) {
			roleDto.setName("ROLE_" + roleDto.getName());
        }
        
        role.setName(roleDto.getName());

		role = roleRepository.save(role);

        return new RoleDto(role.getId(), role.getName());
	}

	public Set<RoleDto> getRoles() {
		List<Role> roles = roleRepository.findAll();
		
		Set<RoleDto> roleResponse = new HashSet<>();
		for (Role role : roles) {
			roleResponse.add(new RoleDto(role.getId(), role.getName()));
		}
		
		return roleResponse;
	}
}
