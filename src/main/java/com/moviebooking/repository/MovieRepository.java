package com.moviebooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebooking.model.Movie;
import com.moviebooking.model.MovieIdentity;

@Repository
public interface MovieRepository extends CrudRepository<Movie, MovieIdentity> {

}
