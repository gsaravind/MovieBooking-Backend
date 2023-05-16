package com.moviebooking.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moviebooking.model.JwtRequest;
import com.moviebooking.model.User;
import com.moviebooking.repository.UserRepository;
import com.moviebooking.util.JwtUtil;
import com.moviebooking.wrapperclasses.UserPojo;

@Service
public class JwtService implements UserDetailsService {

	static final Logger log = LoggerFactory.getLogger(JwtService.class);

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserPojo createJwtToken(JwtRequest jwtRequest) throws BadCredentialsException, UsernameNotFoundException {
		String userName = jwtRequest.getLoginId();
		String userPassword = jwtRequest.getPassword();
		final UserDetails userDetails = loadUserByUsername(userName);
		authenticate(userName, userPassword);
		String token = jwtUtil.generateToken(userDetails);
		User user = userRepository.findById(userName).get();
		UserPojo pojo = new UserPojo(user.getLoginId(), user.getFirstName(), user.getLastName(), user.getEmailId(),
				user.getContactNumber(), token);
		log.info("Token created successfully for the request {}", jwtRequest);
		return pojo;
	}

	private void authenticate(String userName, String password) throws BadCredentialsException, DisabledException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			log.warn("User is disabled userName {}", userName);
			throw new DisabledException("User is disabled");
		} catch (BadCredentialsException e) {
			log.warn("Credentials are invalid for userName {}", userName);
			throw new BadCredentialsException("Invalid Credentials");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findById(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
					getAuthorities(user));
		} else {
			log.warn("UserName is invalid {}", username);
			throw new UsernameNotFoundException("Username is invalid");
		}
	}

	private Set<SimpleGrantedAuthority> getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

}
