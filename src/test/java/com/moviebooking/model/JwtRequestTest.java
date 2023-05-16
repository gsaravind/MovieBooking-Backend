package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class JwtRequestTest {

	@Test
	public void testGetLoginId() {
		JwtRequest jwtRequest = new JwtRequest("testuser", "password");
		assertEquals("testuser", jwtRequest.getLoginId());
	}

	@Test
	public void testSetLoginId() {
		JwtRequest jwtRequest = new JwtRequest("testuser", "password");
		jwtRequest.setLoginId("newuser");
		assertEquals("newuser", jwtRequest.getLoginId());
	}

	@Test
	public void testGetPassword() {
		JwtRequest jwtRequest = new JwtRequest("testuser", "password");
		assertEquals("password", jwtRequest.getPassword());
	}

	@Test
	public void testSetPassword() {
		JwtRequest jwtRequest = new JwtRequest("testuser", "password");
		jwtRequest.setPassword("newpassword");
		assertEquals("newpassword", jwtRequest.getPassword());
	}

	@Test
	public void testConstructor() {
		JwtRequest jwtRequest = new JwtRequest("testuser", "password");
		assertEquals("testuser", jwtRequest.getLoginId());
		assertEquals("password", jwtRequest.getPassword());
	}
}
