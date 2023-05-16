package com.moviebooking.wrapperclasses;

import java.util.List;

public class TicketPojo {
	private String movieName;
	private String theatreName;
	private int totNoOfTickets;
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

	public int getTotNoOfTickets() {
		return totNoOfTickets;
	}

	public void setTotNoOfTickets(int totNoOfTickets) {
		this.totNoOfTickets = totNoOfTickets;
	}

	public List<String> getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(List<String> seatNo) {
		this.seatNo = seatNo;
	}

	public TicketPojo(String movieName, String theatreName, int totNoOfTickets, List<String> seatNo) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.totNoOfTickets = totNoOfTickets;
		this.seatNo = seatNo;
	}

	public TicketPojo() {
	}

	@Override
	public String toString() {
		return "TicketPojo [movieName=" + movieName + ", theatreName=" + theatreName + ", totNoOfTickets="
				+ totNoOfTickets + ", seatNo=" + seatNo + "]";
	}

}
