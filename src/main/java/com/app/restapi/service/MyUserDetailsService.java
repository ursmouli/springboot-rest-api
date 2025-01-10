package com.app.restapi.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.restapi.jpa.repo.ContactDetailsRepository;
import com.app.restapi.jpa.entity.ContactDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ContactDetails contactDetails = contactDetailsRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new User(contactDetails.getUsername(), 
						contactDetails.getPassword(), 
						contactDetails.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
	}

}
