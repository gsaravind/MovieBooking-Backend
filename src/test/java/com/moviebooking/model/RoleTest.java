package com.moviebooking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class RoleTest {

	@Test
	public void testGettersAndSetters() {
		Role role = new Role();
		role.setRoleName("admin");
		role.setRoleDescription("Administrator role");

		assertEquals("admin", role.getRoleName());
		assertEquals("Administrator role", role.getRoleDescription());
	}

	@Test
	public void testConstructor() {
		Role role = new Role("admin", "Administrator role");

		assertEquals("admin", role.getRoleName());
		assertEquals("Administrator role", role.getRoleDescription());
	}
}
