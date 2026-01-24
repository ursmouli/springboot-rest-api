package com.app.restapi.initializer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.app.restapi.jpa.entity.ContactDetails;
import com.app.restapi.jpa.repo.ContactDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.restapi.jpa.repo.RoleRepository;
import com.app.restapi.jpa.entity.Role;

@Component
@Order(1)
public class UserRoleInitializer implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleInitializer.class);

	private final RoleRepository roleRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRoleInitializer(RoleRepository roleRepository,
                               ContactDetailsRepository contactDetailsRepository,
                               PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.contactDetailsRepository = contactDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }


	@Override
	public void run(String... args) throws Exception {
		// Define the roles you want to create
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        // Check if roles already exist and create them if they don't
        for (String roleName : roles) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }

        Role admin = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Role Admin not found"));
        Role user = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role User not found"));

        // default admin
        ContactDetails adminContactDetails = new ContactDetails()
                .setFirstName("John")
                .setLastName("Smith")
                .setDob(new Date())
                .setRoles(Set.of(admin))
                .setEmail("john.smith@admin.com")
                .setPassword(passwordEncoder.encode("password"));

        contactDetailsRepository.save(adminContactDetails);
        LOG.info("Default admin {}", adminContactDetails);

        // default user
        ContactDetails userContactDetails = new ContactDetails()
                .setFirstName("John")
                .setLastName("Doe")
                .setDob(new Date())
                .setRoles(Set.of(user))
                .setEmail("john.doe@user.com")
                .setPassword(passwordEncoder.encode("password"));

        contactDetailsRepository.save(userContactDetails);

        LOG.info("Default user {}", userContactDetails);
	}
}
