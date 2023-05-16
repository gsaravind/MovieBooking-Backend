package com.moviebooking.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.*;
import org.junit.jupiter.api.Test;

public class TicketsTest {

	@Test
	public void testGettersSetters() {
		List<String> lst = new ArrayList<>();
		lst.add("A1");
		lst.add("A2");
		Tickets ticket = new Tickets();
		ticket.setMovieName("MovieName");
		ticket.setNoOfTicketsBooked(101);
		ticket.setTicketUpdateStatus(true);
		ticket.setTheatreName("TheatreName");
		ticket.setSeatNo(lst);
		ticket.toString();
		assertEquals(ticket.getMovieName(), "MovieName");
		assertEquals(ticket.getNoOfTicketsBooked(), 101);
		assertEquals(ticket.getSeatNo(), lst);
		assertEquals(ticket.getTheatreName(), "TheatreName");
		assertEquals(ticket.isTicketUpdateStatus(), true);
	}
	
	@Test
	public void testConstructor() {
		List<String> lst = new ArrayList<>();
		lst.add("A1");
		lst.add("A2");
		Tickets ticket = new Tickets("MovieName", "TheatreName", 101, lst);
		assertEquals(ticket.getMovieName(), "MovieName");
		assertEquals(ticket.getNoOfTicketsBooked(), 101);
		assertEquals(ticket.getSeatNo(), lst);
		assertEquals(ticket.getTheatreName(), "TheatreName");
	}
}
