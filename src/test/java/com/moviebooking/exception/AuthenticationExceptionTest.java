package com.moviebooking.exception;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class AuthenticationExceptionTest {

	@Test
	public void testAuthenticationException() {
		AuthenticationException exception = new AuthenticationException("authenticationexception");
		exception.setMessage("exception");
		assertEquals(exception.getMessage(), "exception");
	}
	
	@Test
	public void testToString() {
		AuthenticationException exception = new AuthenticationException("authenticationexception");
		assertEquals(exception.toString(), "AuthenticationException [message=authenticationexception]");
	}
}
