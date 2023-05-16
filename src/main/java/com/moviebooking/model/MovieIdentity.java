package com.moviebooking.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class MovieIdentity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String movieName;
	private String theatreName;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public MovieIdentity(String movieName, String theatreName) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
	}

	public MovieIdentity() {
	}

	@Override
	public String toString() {
		return "MovieIdentity [movieName=" + movieName + ", theatreName=" + theatreName + "]";
	}

}
