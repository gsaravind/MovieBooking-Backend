package com.moviebooking.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class MovieTest {
	@Test
	public void checkGetterSetters() {
		MovieIdentity mI = new MovieIdentity("Movie Name", "Theatre Name");
		Movie movie = new Movie();
		movie.setMovieIdentity(mI);
		movie.setNoOfTickets(101);
		assertEquals(movie.getMovieIdentity(), mI);
		assertEquals(movie.getNoOfTickets(), 101);
	}

	@Test
	public void checkConstructors() {
		MovieIdentity mI = new MovieIdentity("Movie Name", "Theatre Name");
		Movie movie = new Movie(mI, 101);
		assertEquals(movie.getMovieIdentity(), mI);
		assertEquals(movie.getNoOfTickets(), 101);
	}
}
