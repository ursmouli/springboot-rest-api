package com.app.restapi.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.restapi.dto.LoginUserDto;
import com.app.restapi.dto.RegisterUserDto;
import com.app.restapi.jpa.dao.ContactDetailsRepository;
import com.app.restapi.jpa.dao.RoleRepository;
import com.app.restapi.jpa.entity.ContactDetails;
import com.app.restapi.jpa.entity.Role;

@Service
public class AuthenticationService {

	private final ContactDetailsRepository contactDetailsRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationService(
			ContactDetailsRepository contactDetailsRepository,
			PasswordEncoder passwordEncoder,
			RoleRepository roleRepository,
			AuthenticationManager authenticationManager
	) {
		this.contactDetailsRepository = contactDetailsRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.authenticationManager = authenticationManager;
	}
	
	public ContactDetails signup(RegisterUserDto input) {
		ContactDetails contactDetails = new ContactDetails();
		
		contactDetails.setFullName(input.getFullName());
		contactDetails.setEmail(input.getEmail());
		contactDetails.setEmail(input.getEmail());
		contactDetails.setPassword(passwordEncoder.encode(input.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		for (String roleName : input.getRoles()) {
			Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found:" + roleName));
			roles.add(role);
		}
		
		if (!CollectionUtils.isEmpty(roles)) {
			contactDetails.setRoles(roles);
		}
		
		return contactDetailsRepository.save(contactDetails);
	}
	
	public ContactDetails authenticate(LoginUserDto input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getEmail(), 
						input.getPassword()
				)
		);
		
		return contactDetailsRepository.findByEmail(input.getEmail()).orElseThrow();
	}
}
