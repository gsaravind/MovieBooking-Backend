package com.moviebooking.exception;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class CustomExceptionTest {

	@Test
	public void testCustomException() {
		CustomException exception = new CustomException("customexception");
		exception.setMessage("exception");
		assertEquals(exception.getMessage(), "exception");
	}
	
	@Test
	public void testToString() {
		CustomException exception = new CustomException("customexception");
		assertEquals(exception.toString(), "CustomException [msg=customexception]");
	}
}
