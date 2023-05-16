package com.moviebooking.wrapperclasses;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.*;
import org.junit.jupiter.api.Test;

import com.moviebooking.model.MovieIdentity;

public class MoviePojoTest {
	
	@Test
	public void testGetterSetters() {
		MovieIdentity id = new MovieIdentity("smile", "funbox");
		List<String> bookedSeats = Arrays.asList("A1", "A2");
		MoviePojo pojo = new MoviePojo(id, 10, bookedSeats, true);
		pojo.setBookedSeats(bookedSeats);
		assertEquals(pojo.isStatusUpdated(), true);
		assertEquals(pojo.getBookedSeats().get(0), "A1");
		assertEquals(pojo.getNoOfTickets(), 10);
		assertEquals(pojo.getMovieIdentity().getMovieName(), "smile");
		assertEquals(pojo.getMovieIdentity().getTheatreName(), "funbox");
	}
}
