package com.moviebooking.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.HashSet;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebooking.model.JwtRequest;
import com.moviebooking.model.User;
import com.moviebooking.service.JwtService;
import com.moviebooking.service.UserService;
import com.moviebooking.wrapperclasses.SimpleResponse;
import com.moviebooking.wrapperclasses.UserPojo;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private JwtService jwtService;

	@InjectMocks
	private UserController userController;

	@DisplayName("JUnit test for init role and user method")
	@Test
	public void testInitRoleAndUser() {
		doNothing().when(userService).initRoleAndUser();
		Assertions.assertDoesNotThrow(() -> userController.initRoleAndUser());
	}

	@DisplayName("JUnit test for register a user method")
	@Test
	public void testRegisterUser() throws Exception {
		User user = new User("msaraswat", "manmohan", "saraswat", "12345", "m@gmail.com", "9358342345",
				new HashSet<>());
		when(userService.registerNewUser(any(User.class))).thenReturn(any(User.class));
		String content = (new ObjectMapper()).writeValueAsString(user);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/moviebooking/register")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@DisplayName("JUnit test for login a user method")
	@Test
	public void testLoginUser() throws Exception {
		when(jwtService.createJwtToken(any(JwtRequest.class))).thenReturn(new UserPojo());
		JwtRequest request = new JwtRequest("msaraswat", "12345");
		String content = (new ObjectMapper()).writeValueAsString(request);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/moviebooking/login")
				.contentType(MediaType.APPLICATION_JSON).content(content);
		MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		.andExpect(MockMvcResultMatchers.content().string("{\"loginId\":null,\"firstName\":null,\"lastName\":null,\"emailId\":null,\"contactNumber\":null,\"jwtToken\":null}"));
	}

	@DisplayName("JUnit test for fogot username method")
	@Test
	public void testForgotUser() throws Exception {
		User user = new User("msaraswat", "manmohan", "saraswat", "12345", "m@gmail.com", "9358342345",
				new HashSet<>());
		when(userService.forgotPassword(any(User.class)))
				.thenReturn(new SimpleResponse("Password updated successfully"));
		String content = (new ObjectMapper()).writeValueAsString(user);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/v1.0/moviebooking/forgot/msaraswat").contentType(MediaType.APPLICATION_JSON)
				.content(content);
		MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Password updated successfully\"}"));
	}

	@Test
	@WithMockUser(authorities = "Admin")
	void test_roleAdmin() {
		Assertions.assertDoesNotThrow(() -> this.userController.forAdmin());
	}

	@Test
	@WithMockUser(authorities = "User")
	void test_roleUser() {
		Assertions.assertDoesNotThrow(() -> this.userController.forUser());
	}
}
