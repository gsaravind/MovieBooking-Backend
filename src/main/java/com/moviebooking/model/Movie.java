package com.moviebooking.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@EmbeddedId
	private MovieIdentity movieIdentity;

	private int noOfTickets;

	public MovieIdentity getMovieIdentity() {
		return movieIdentity;
	}

	public void setMovieIdentity(MovieIdentity movieIdentity) {
		this.movieIdentity = movieIdentity;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public Movie(MovieIdentity movieIdentity, int noOfTickets) {
		super();
		this.movieIdentity = movieIdentity;
		this.noOfTickets = noOfTickets;
	}

	public Movie() {
	}

	@Override
	public String toString() {
		return "Movie [movieIdentity=" + movieIdentity + ", noOfTickets=" + noOfTickets + "]";
	}
}
