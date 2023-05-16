package com.moviebooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.model.Role;
import com.moviebooking.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	static final Logger log = LoggerFactory.getLogger(RoleService.class);

	public Role createNewRole(Role role) {
		log.info("New Role added successfully {}", role);
		return roleRepository.save(role);
	}
}
