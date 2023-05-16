package com.moviebooking.wrapperclasses;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class SimpleResponseTest {

	@Test
	public void testGetterSetters() {
		SimpleResponse response = new SimpleResponse();
		response.setMessage("Response");
		assertEquals(response.getMessage(), "Response");
	}

	@Test
	public void testConstructors() {
		SimpleResponse response = new SimpleResponse("Response");
		assertEquals(response.getMessage(), "Response");
	}
}
