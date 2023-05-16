package com.moviebooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebooking.model.MovieIdentity;
import com.moviebooking.model.Tickets;

@Repository
public interface TicketsRepository extends CrudRepository<Tickets, MovieIdentity>{

}
