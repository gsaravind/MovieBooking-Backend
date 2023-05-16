package com.moviebooking.wrapperclasses;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class UserPojoTest {

	@Test
	public void testGettersSetters() {
		UserPojo pojo = new UserPojo();
		pojo.setFirstName("manmohan");
		pojo.setLastName("saraswat");
		pojo.setContactNumber("12345");
		pojo.setEmailId("m@gmail.com");
		pojo.setJwtToken("token");
		pojo.setLoginId("msaraswat");
		assertEquals(pojo.getFirstName(), "manmohan");
		assertEquals(pojo.getLastName(), "saraswat");
		assertEquals(pojo.getContactNumber(), "12345");
		assertEquals(pojo.getEmailId(), "m@gmail.com");
		assertEquals(pojo.getJwtToken(), "token");
		assertEquals(pojo.getLoginId(), "msaraswat");
	}

	@Test
	public void testConstructors() {
		UserPojo pojo = new UserPojo("msaraswat", "manmohan", "saraswat", "m@gmail.com", "12345", "token");
		assertEquals(pojo.getFirstName(), "manmohan");
		assertEquals(pojo.getLastName(), "saraswat");
		assertEquals(pojo.getContactNumber(), "12345");
		assertEquals(pojo.getEmailId(), "m@gmail.com");
		assertEquals(pojo.getJwtToken(), "token");
		assertEquals(pojo.getLoginId(), "msaraswat");
	}
}
