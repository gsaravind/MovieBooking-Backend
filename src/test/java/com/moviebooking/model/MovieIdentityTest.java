package com.moviebooking.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieIdentityTest {

	@Test
	public void testGettersAndSetters() {
		// Arrange
		String movieName = "The Matrix";
		String theatreName = "Cineplex";
		MovieIdentity movieIdentity = new MovieIdentity(movieName, theatreName);
		movieIdentity.setMovieName(movieName);
		movieIdentity.setTheatreName(theatreName);

		// Act
		String resultMovieName = movieIdentity.getMovieName();
		String resultTheatreName = movieIdentity.getTheatreName();

		// Assert
		Assertions.assertEquals(movieName, resultMovieName);
		Assertions.assertEquals(theatreName, resultTheatreName);
	}

	@Test
	public void testConstructors() {
		// Arrange
		String movieName1 = "The Matrix";
		String theatreName1 = "Cineplex";
		MovieIdentity movieIdentity1 = new MovieIdentity(movieName1, theatreName1);


		// Assert
		Assertions.assertTrue(movieIdentity1.getMovieName().equals("The Matrix"));
		Assertions.assertTrue(movieIdentity1.getTheatreName().equals("Cineplex"));
	}
}
