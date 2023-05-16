package com.moviebooking.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import java.util.Optional;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.Role;
import com.moviebooking.model.User;
import com.moviebooking.repository.RoleRepository;
import com.moviebooking.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	private Role role;
	private User user;

	@BeforeEach
	public void SetUp() {
		role = new Role("Admin", "Admin role for the application");
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		user = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cognizant.com", "+91 9358342345", set);
	}

	@DisplayName("JUnit test for init Role and user method")
	@Test
	public void testInitRoleAndUser() {
		given(roleRepository.save(any(Role.class))).willReturn(role);
		given(userRepository.save(any(User.class))).willReturn(user);
		Assertions.assertDoesNotThrow(() -> userService.initRoleAndUser());
	}

	@DisplayName("JUnit test for register new user method")
	@Test
	public void testRegisterNewUser() {
		given(userRepository.existsById(any(String.class))).willReturn(false);
		given(roleRepository.findById(any(String.class))).willReturn(Optional.of(role));
		given(userRepository.save(any(User.class))).willReturn(user);
		Assertions.assertDoesNotThrow(() -> userService.registerNewUser(user));
	}
	
	@DisplayName("JUnit test for register new user method throws custom exception")
	@Test
	public void testRegisterNewUserThrowsCustomException() {
		given(userRepository.existsById(any(String.class))).willReturn(true);
		Assertions.assertThrows(CustomException.class, () -> userService.registerNewUser(user));
	}

	@DisplayName("JUnit test for forgot password method")
	@Test
	public void testForgotPassword() {
		given(userRepository.findById(any(String.class))).willReturn(Optional.of(user));
		given(userRepository.save(any(User.class))).willReturn(user);
		Assertions.assertDoesNotThrow(() -> userService.forgotPassword(user));
	}

	@DisplayName("JUnit test for forgot password method does not find the request user")
	@Test
	public void testForgotPasswordThrowsCustomExceptionUserNotFound() {
		given(userRepository.findById(any(String.class))).willReturn(Optional.empty());
		Assertions.assertThrows(CustomException.class, () -> userService.forgotPassword(user));
	}

	@DisplayName("JUnit test for forgot password method contact number does not match")
	@Test
	public void testForgotPasswordThrowsCustomExceptionContactNoNotMatch() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user1 = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cognizant.com", "+918342345",
				set);
		given(userRepository.findById(any(String.class))).willReturn(Optional.of(user1));
		Assertions.assertThrows(CustomException.class, () -> userService.forgotPassword(user));
	}

	@DisplayName("JUnit test for forgot password method first name does not match")
	@Test
	public void testForgotPasswordThrowsCustomExceptionFirstNameNotMatch() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user1 = new User("msaraswat", "Manmoh", "Saraswat", "12345", "msaraswat@cognizant.com", "+91 9358342345",
				set);
		given(userRepository.findById(any(String.class))).willReturn(Optional.of(user1));
		Assertions.assertThrows(CustomException.class, () -> userService.forgotPassword(user));
	}

	@DisplayName("JUnit test for forgot password method last name does not match")
	@Test
	public void testForgotPasswordThrowsCustomExceptionLastNameNotMatch() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user1 = new User("msaraswat", "Manmohan", "Saras", "12345", "msaraswat@cognizant.com", "+91 9358342345",
				set);
		given(userRepository.findById(any(String.class))).willReturn(Optional.of(user1));
		Assertions.assertThrows(CustomException.class, () -> userService.forgotPassword(user));
	}

	@DisplayName("JUnit test for forgot password method email id does not match")
	@Test
	public void testForgotPasswordThrowsCustomExceptionEmailIdNotMatch() {
		Set<Role> set = new HashSet<>(Arrays.asList(new Role("Admin", "Admin role for the application")));
		User user1 = new User("msaraswat", "Manmohan", "Saraswat", "12345", "msaraswat@cogniza", "+91 9358342345", set);
		given(userRepository.findById(any(String.class))).willReturn(Optional.of(user1));
		Assertions.assertThrows(CustomException.class, () -> userService.forgotPassword(user));
	}

}
