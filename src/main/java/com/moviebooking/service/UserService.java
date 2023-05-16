package com.moviebooking.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.JwtRequest;
import com.moviebooking.model.Role;
import com.moviebooking.model.User;
import com.moviebooking.repository.RoleRepository;
import com.moviebooking.repository.UserRepository;
import com.moviebooking.wrapperclasses.SimpleResponse;

@Service
public class UserService {

	static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerNewUser(User user) throws CustomException {
		if (userRepository.existsById(user.getLoginId())) {
			log.warn("User with loginId {} already exists", user.getLoginId());
			throw new CustomException("User with loginId: " + user.getLoginId() + " already exists");
		}
		Role role = roleRepository.findById("User").get();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		user.setPassword(getEncodedPassword(user.getPassword()));
		log.info("User added successfully to the database {}", user);
		return userRepository.save(user);
	}

	public void initRoleAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role for the application");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("User role for the application");
		roleRepository.save(userRole);

		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);

		User adminUser = new User("Admin", "Admin", "Admin", "admin", "admin@cognizant.com", "9358342345", adminRoles);
		adminUser.setPassword(getEncodedPassword("admin"));
		userRepository.save(adminUser);
		log.info("User added successfully to the database {}", adminUser);

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);

		User user = new User("msaraswat", "Manmohan", "Saraswat", "12345", "manmohan@cognizant.com", "9358342345",
				userRoles);
		user.setPassword(getEncodedPassword("12345"));
		userRepository.save(user);
		log.info("User added successfully to the database {}", user);

	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public SimpleResponse forgotPassword(User request) throws CustomException {
		Optional<User> opUser = userRepository.findById(request.getLoginId());
		if (opUser.isEmpty()) {
			log.warn("User with username {} not found", request.getLoginId());
			throw new CustomException("Username not found");
		}
		User user = opUser.get();
		if (!user.getContactNumber().equals(request.getContactNumber())) {
			log.warn("Contact number doesn't match unable to update the password for user with login id {}",
					request.getLoginId());
			throw new CustomException("Details not matched, Unable to update password");
		}
		if (!user.getFirstName().equals(request.getFirstName())) {
			log.warn("First name doesn't match unable to update the password for user with login id {}",
					request.getLoginId());
			throw new CustomException("Details not matched, Unable to update password");
		}
		if (!user.getLastName().equals(request.getLastName())) {
			log.warn("Last name doesn't match unable to update the password for user with login id {}",
					request.getLoginId());
			throw new CustomException("Details not matched, Unable to update password");
		}
		if (!user.getEmailId().equals(request.getEmailId())) {
			log.warn("Email id doesn't match unable to update the password for user with login id {}",
					request.getLoginId());
			throw new CustomException("Details not matched, Unable to update password");
		}
		user.setPassword(getEncodedPassword(request.getPassword()));
		userRepository.save(user);
		log.info("Password for the user with user id {} updated successfully", user.getLoginId());
		return new SimpleResponse("Password updated successfully");
	}
}
