package com.moviebooking.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.moviebooking.model.Role;
import com.moviebooking.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private RoleService roleService;

	private Role role;

	@BeforeEach
	public void setup() {
		role = new Role("Admin", "Admin role description for the application");
	}

	@DisplayName("JUnit test for save role method")
	@Test
	public void saveNewRole() {
		given(roleRepository.save(role)).willReturn(role);
		Role role1 = roleService.createNewRole(role);
		assertThat(role1).isNotNull();
	}

}
