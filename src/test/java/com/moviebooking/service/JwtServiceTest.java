package com.moviebooking.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import java.util.Optional;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.moviebooking.model.JwtRequest;
import com.moviebooking.model.Role;
import com.moviebooking.model.User;
import com.moviebooking.repository.UserRepository;
import com.moviebooking.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AuthenticationManager authenticationManager;

	@InjectMocks
	private JwtService jwtService;

	@BeforeEach
	public void setup() {

	}

	@DisplayName("JUnit test for create Jwt Token")
	@Test
	public void testCreateJwtToken() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cognizant.com", "+91 9358342345",
				set);
		Optional<User> op = Optional.of(user);
		given(userRepository.findById("msaraswat")).willReturn(op);
		JwtRequest request = new JwtRequest("msaraswat", "12345");
		jwtService.createJwtToken(request);
	}

	@DisplayName("JUnit test for create Jwt Token throws disabled exception")
	@Test
	public void testCreateJwtTokenThrowsDisabledException() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cognizant.com", "+91 9358342345",
				set);
		Optional<User> op = Optional.of(user);
		given(userRepository.findById("msaraswat")).willReturn(op);
		given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("msaraswat", "12345")))
				.willThrow(DisabledException.class);
		JwtRequest request = new JwtRequest("msaraswat", "12345");
		Assertions.assertThrows(DisabledException.class, () -> jwtService.createJwtToken(request));
	}

	@DisplayName("JUnit test for create Jwt Token throws bad credentials exception")
	@Test
	public void testCreateJwtTokenThrowsBadCredentialsException() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cognizant.com", "+91 9358342345",
				set);
		Optional<User> op = Optional.of(user);
		given(userRepository.findById("msaraswat")).willReturn(op);
		given(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("msaraswat", "12345")))
				.willThrow(BadCredentialsException.class);
		JwtRequest request = new JwtRequest("msaraswat", "12345");
		Assertions.assertThrows(BadCredentialsException.class, () -> jwtService.createJwtToken(request));
	}

	@DisplayName("JUnit test for create Jwt Token throws username not found exception")
	@Test
	public void testCreateJwtTokenThrowsUsernameNotFoundException() {
		JwtRequest request = new JwtRequest("msaraswat", "12345");
		Assertions.assertThrows(UsernameNotFoundException.class, () -> jwtService.createJwtToken(request));
	}

	@DisplayName("JUnit test for get all movies method")
	@Test
	public void testLoadUserByUserName() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cognizant.com", "+91 9358342345",
				set);
		Optional<User> op = Optional.of(user);
		given(userRepository.findById("manmohan")).willReturn(op);
		Assertions.assertDoesNotThrow(() -> jwtService.loadUserByUsername("manmohan"));
	}

	@DisplayName("JUnit test for get all movies method throws exception")
	@Test
	public void testLoadUserByUserNameThrowsException() {
		Assertions.assertThrows(UsernameNotFoundException.class, () -> jwtService.loadUserByUsername("manmohan"));
	}

}
