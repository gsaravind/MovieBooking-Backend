package com.moviebooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebooking.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
