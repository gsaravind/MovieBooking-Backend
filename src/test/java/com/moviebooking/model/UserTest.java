package com.moviebooking.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class UserTest {

    @Test
    public void testGetLoginId() {
        User user = new User();
        user.setLoginId("testuser");
        assertEquals("testuser", user.getLoginId());
    }

    @Test
    public void testSetLoginId() {
        User user = new User();
        user.setLoginId("testuser");
        assertEquals("testuser", user.getLoginId());
    }

    @Test
    public void testGetFirstName() {
        User user = new User();
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        User user = new User();
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        User user = new User();
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testSetLastName() {
        User user = new User();
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testGetPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetEmailId() {
        User user = new User();
        user.setEmailId("test@test.com");
        assertEquals("test@test.com", user.getEmailId());
    }

    @Test
    public void testSetEmailId() {
        User user = new User();
        user.setEmailId("test@test.com");
        assertEquals("test@test.com", user.getEmailId());
    }

    @Test
    public void testGetContactNumber() {
        User user = new User();
        user.setContactNumber("1234567890");
        assertEquals("1234567890", user.getContactNumber());
    }

    @Test
    public void testSetContactNumber() {
        User user = new User();
        user.setContactNumber("1234567890");
        assertEquals("1234567890", user.getContactNumber());
    }

    @Test
    public void testGetRole() {
        User user = new User();
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role("ROLE_ADMIN", "Admin Role for the application"));
        user.setRole(roles);
        assertEquals(roles, user.getRole());
    }

    @Test
    public void testSetRole() {
        User user = new User();
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role("ROLE_ADMIN", "Admin Role for the application"));
        user.setRole(roles);
        assertEquals(roles, user.getRole());
    }

    @Test
    public void testConstructor() {
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role("ROLE_ADMIN", "Admin Role for the application"));
        User user = new User("testuser", "John", "Doe", "password", "test@test.com", "1234567890", roles);
        assertEquals("testuser", user.getLoginId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("password", user.getPassword());
        assertEquals("test@test.com", user.getEmailId());
        assertEquals("1234567890", user.getContactNumber());
    }
}

