package com.moviebooking.wrapperclasses;

import java.util.List;

import com.moviebooking.model.MovieIdentity;

public class MoviePojo {
	private MovieIdentity movieIdentity;
	private int noOfTickets;
	private List<String> bookedSeats;
	private boolean isStatusUpdated;

	public boolean isStatusUpdated() {
		return isStatusUpdated;
	}

	public void setStatusUpdated(boolean isStatusUpdated) {
		this.isStatusUpdated = isStatusUpdated;
	}

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

	public List<String> getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(List<String> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public MoviePojo(MovieIdentity movieIdentity, int noOfTickets, List<String> bookedSeats) {
		super();
		this.movieIdentity = movieIdentity;
		this.noOfTickets = noOfTickets;
		this.bookedSeats = bookedSeats;
	}

	public MoviePojo(MovieIdentity movieIdentity, int noOfTickets, List<String> bookedSeats, boolean isStatusUpdated) {
		super();
		this.movieIdentity = movieIdentity;
		this.noOfTickets = noOfTickets;
		this.bookedSeats = bookedSeats;
		this.isStatusUpdated = isStatusUpdated;
	}

	public MoviePojo() {
	}
}
