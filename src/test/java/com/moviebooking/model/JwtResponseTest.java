package com.moviebooking.model;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class JwtResponseTest {

    @Test
    public void testConstructorAndGetters() {
        User user = new User("testuser", "John", "Doe", "password", "johndoe@example.com", "1234567890",
                getTestRoles());
        JwtResponse jwtResponse = new JwtResponse(user, "testJwtToken");
        assertEquals(user, jwtResponse.getUser());
        assertEquals("testJwtToken", jwtResponse.getJwtToken());
    }

    @Test
    public void testSetters() {
        User user = new User("testuser", "John", "Doe", "password", "johndoe@example.com", "1234567890",
                getTestRoles());
        JwtResponse jwtResponse = new JwtResponse(user, "testJwtToken");
        User newUser = new User("newtestuser", "Jane", "Doe", "newpassword", "janedoe@example.com", "0987654321",
                getTestRoles());
        jwtResponse.setUser(newUser);
        jwtResponse.setJwtToken("newJwtToken");
        assertEquals(newUser, jwtResponse.getUser());
        assertEquals("newJwtToken", jwtResponse.getJwtToken());
    }

    @Test
    public void testToString() {
        User user = new User("testuser", "John", "Doe", "password", "johndoe@example.com", "1234567890",
                getTestRoles());
        JwtResponse jwtResponse = new JwtResponse(user, "testJwtToken");
        JwtResponse jwtResponse1 = new JwtResponse(user, "testJwtToken");
        assertEquals(jwtResponse1.getJwtToken(), jwtResponse.getJwtToken());
        jwtResponse.toString();
        assertEquals(jwtResponse1.getJwtToken(), jwtResponse.getJwtToken());
    }

    private Set<Role> getTestRoles() {
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role("ROLE_ADMIN", "Adin role for the application");
        Role role2 = new Role("ROLE_USER", "User role for the application");
        roles.add(role1);
        roles.add(role2);
        return roles;
    }

}
