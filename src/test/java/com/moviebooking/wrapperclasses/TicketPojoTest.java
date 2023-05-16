package com.moviebooking.wrapperclasses;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.*;
import org.junit.jupiter.api.Test;

public class TicketPojoTest {

	@Test
	public void testGetterSetters() {
		TicketPojo pojo = new TicketPojo();
		pojo.setMovieName("movie");
		pojo.setTheatreName("theatre");
		pojo.setTotNoOfTickets(101);
		List<String> seatNo = Arrays.asList("A1", "A2");
		pojo.setSeatNo(seatNo);
		assertEquals(pojo.getMovieName(), "movie");
		assertEquals(pojo.getTotNoOfTickets(), 101);
		assertEquals(pojo.getTheatreName(), "theatre");
		assertEquals(pojo.getSeatNo().get(0), "A1");
	}

	@Test
	public void testConstructors() {
		TicketPojo pojo = new TicketPojo("movie", "theatre", 101, Arrays.asList("A1", "A2"));
		assertEquals(pojo.getMovieName(), "movie");
		assertEquals(pojo.getTotNoOfTickets(), 101);
		assertEquals(pojo.getTheatreName(), "theatre");
		assertEquals(pojo.getSeatNo().get(0), "A1");
	}
}
