
package com.moviebooking.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
@IdClass(MovieIdentity.class)
public class Tickets {
	@Id
	private String movieName;

	@Id
	private String theatreName;

	private boolean ticketUpdateStatus;

	private int noOfTicketsBooked;
	
	@Column(length=Integer.MAX_VALUE)
	private List<String> seatNo;

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

	public int getNoOfTicketsBooked() {
		return noOfTicketsBooked;
	}

	public void setNoOfTicketsBooked(int noOfTicketsBooked) {
		this.noOfTicketsBooked = noOfTicketsBooked;
	}

	public boolean isTicketUpdateStatus() {
		return ticketUpdateStatus;
	}

	public void setTicketUpdateStatus(boolean ticketUpdateStatus) {
		this.ticketUpdateStatus = ticketUpdateStatus;
	}

	public List<String> getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(List<String> seatNo) {
		this.seatNo = seatNo;
	}

	public Tickets(String movieName, String theatreName, int noOfTicketsBooked, List<String> seatNo) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.noOfTicketsBooked = noOfTicketsBooked;
		this.seatNo = seatNo;
	}

	public Tickets() {
	}

	@Override
	public String toString() {
		return "Tickets [movieName=" + movieName + ", theatreName=" + theatreName + ", ticketUpdateStatus="
				+ ticketUpdateStatus + ", noOfTicketsBooked=" + noOfTicketsBooked + ", seatNo=" + seatNo + "]";
	}

}
