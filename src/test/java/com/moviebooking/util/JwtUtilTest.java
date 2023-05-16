package com.moviebooking.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTest {

	@InjectMocks
	private JwtUtil jwtUtil;

	@Test
	public void testGenerateToken() {
		UserDetails userDetails = mock(UserDetails.class);
		String token = jwtUtil.generateToken(userDetails);

		Claims claims = jwtUtil.getAllClaimsFromToken(token);
		assertEquals(claims.getSubject(), userDetails.getUsername());
	}

	@Test
	public void testValidateToken() {
		Collection<GrantedAuthority> authorities = Collections.emptyList();
		UserDetails userDetails = new User("testuser", "password", authorities);
		String token = Jwts.builder().setSubject(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + JwtUtil.TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, JwtUtil.SECRET_KEY).compact();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		boolean isValid = jwtUtil.validateToken(token, userDetails, request, response);
		assertTrue(isValid);
	}

	@Test
	public void testExpiredToken() {
		Map<String, Object> claims = new HashMap<>();
		UserDetails userDetails = mock(UserDetails.class);
		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis() - 1000000))
				.setExpiration(new Date(System.currentTimeMillis() - 10000))
				.signWith(SignatureAlgorithm.HS512, JwtUtil.SECRET_KEY).compact();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		jwtUtil.validateToken(token, userDetails, request, response);
	}

	@Test
	public void testMalformedToken() {
		UserDetails userDetails = mock(UserDetails.class);
		String token = "malformedToken";

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		jwtUtil.validateToken(token, userDetails, request, response);
	}

	@Test
	public void testIllegalArgumentException() {
		Collection<GrantedAuthority> authorities = Collections.emptyList();
		UserDetails userDetails = new User("testuser", "password", authorities);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		jwtUtil.validateToken("", userDetails, request, response);
	}
	
	@Test
	public void testUnsupportedSignatureException() {
		Collection<GrantedAuthority> authorities = Collections.emptyList();
		UserDetails userDetails = new User("testuser", "password", authorities);
		String token = Jwts.builder().setSubject("testuser")
				.setExpiration(new Date(System.currentTimeMillis() + JwtUtil.TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, JwtUtil.SECRET_KEY + "AAA").compact();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		jwtUtil.validateToken(token, userDetails, request, response);
	}
	
	@Test
	public void testUnsupportedJwtException() {
		
	}
	
	@Test
	public void testUserNameNotSame() {
		Collection<GrantedAuthority> authorities = Collections.emptyList();
		UserDetails userDetails = new User("testuser", "password", authorities);
		String token = Jwts.builder().setSubject("usernameNotSame")
				.setExpiration(new Date(System.currentTimeMillis() + JwtUtil.TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, JwtUtil.SECRET_KEY).compact();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		jwtUtil.validateToken(token, userDetails, request, response);
	}

	@Test
	public void testGetUsernamefromToken() {
		Map<String, Object> claims = new HashMap<>();
		UserDetails userDetails = mock(UserDetails.class);
		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 10000000))
				.signWith(SignatureAlgorithm.HS512, JwtUtil.SECRET_KEY).compact();

		Assertions.assertDoesNotThrow(() -> jwtUtil.getUserNameFromToken(token));
	}

}
