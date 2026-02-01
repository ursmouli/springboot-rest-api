package com.app.restapi.service;

import java.util.HashSet;
import java.util.Set;

import com.app.restapi.model.AppRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.restapi.dto.LoginUserDto;
import com.app.restapi.dto.RegisterUserDto;
import com.app.restapi.jpa.repo.ContactDetailsRepository;
import com.app.restapi.jpa.repo.RoleRepository;
import com.app.restapi.jpa.entity.ContactDetails;

@Service
public class AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

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

		setDefaultUserRoleIfMissing(input);

		ContactDetails contactDetails = new ContactDetails();
		
		contactDetails.setFirstName(input.getFirstName());
		contactDetails.setMiddleName(input.getMiddleName());
		contactDetails.setLastName(input.getLastName());
		contactDetails.setDob(input.getDateOfBirth());
		contactDetails.setEmail(input.getEmail());
		contactDetails.setPassword(passwordEncoder.encode(input.getPassword()));
		
		Set<com.app.restapi.jpa.entity.Role> roles = new HashSet<>();
		for (String roleName : input.getRoles()) {
			com.app.restapi.jpa.entity.Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found:" + roleName));
			roles.add(role);
		}
		
		if (!CollectionUtils.isEmpty(roles)) {
			contactDetails.setRoles(roles);
		}
		
		return contactDetailsRepository.save(contactDetails);
	}

	private void setDefaultUserRoleIfMissing(RegisterUserDto dto) {
		if (CollectionUtils.isEmpty(dto.getRoles())) {
			logger.info("Roles missing for user {} from registration input. Setting default to user role", dto.getEmail());
			dto.setRoles(Set.of("ROL_E" + AppRole.USER.name()));
		}
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
