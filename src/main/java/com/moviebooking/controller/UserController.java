package com.moviebooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.JwtRequest;
import com.moviebooking.model.User;
import com.moviebooking.service.JwtService;
import com.moviebooking.service.UserService;
import com.moviebooking.wrapperclasses.SimpleResponse;
import com.moviebooking.wrapperclasses.UserPojo;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("api/v1.0/moviebooking")
@CrossOrigin
public class UserController {

	static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public SimpleResponse registerUser(@RequestBody User user) throws CustomException {
		userService.registerNewUser(user);
		log.info("New user registered successfully {}", user);
		return new SimpleResponse("User registered Successfully");
	}

	@PostConstruct
	public void initRoleAndUser() {
		log.info("Initializing role and user");
		userService.initRoleAndUser();
	}

	@PostMapping("/login")
	public UserPojo createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		UserPojo pojo = jwtService.createJwtToken(jwtRequest);
		log.info("Token created successfully for the request : {}", jwtRequest);
		return pojo;
	}

	@PostMapping("/forgot/{userName}")
	public SimpleResponse forgotPassword(@PathVariable String userName, @RequestBody User jwtRequest) throws Exception {
		jwtRequest.setLoginId(userName);
		SimpleResponse response = userService.forgotPassword(jwtRequest);
		log.info("Password reset operation successfull for the user : {}", userName);
		return response;
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	@CrossOrigin
	public SimpleResponse forAdmin() {
		return new SimpleResponse("You are authenticated as admin");
	}
		
	@GetMapping("/user")
	@PreAuthorize("hasRole('User')")
	@CrossOrigin
	public SimpleResponse forUser() {
		return new SimpleResponse("You are authenticated as user");
	}
}