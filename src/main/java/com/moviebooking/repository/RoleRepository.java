package com.moviebooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebooking.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{
	
}
