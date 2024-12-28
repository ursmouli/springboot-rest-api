package com.app.restapi.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.dto.LoginResponse;
import com.app.restapi.dto.LoginUserDto;
import com.app.restapi.dto.RegisterUserDto;
import com.app.restapi.jpa.entity.ContactDetails;
import com.app.restapi.service.AuthenticationService;
import com.app.restapi.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationResource {

	private final JwtService jwtService;
	private final AuthenticationService authenticationService;
	
	public AuthenticationResource(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ContactDetails> register(@RequestBody RegisterUserDto registerUserDto) {
		ContactDetails concatDetails = authenticationService.signup(registerUserDto);
		
		return ResponseEntity.ok(concatDetails);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
		ContactDetails concatDetails = authenticationService.authenticate(loginUserDto);
		
		String jwtToken = jwtService.generateToken(concatDetails);
		
		LoginResponse loginResponse = new LoginResponse()
				.setToken(jwtToken)
				.setExpiresIn(jwtService.getJwtExpirationTime());
		
		return ResponseEntity.ok(loginResponse);
	}
}
