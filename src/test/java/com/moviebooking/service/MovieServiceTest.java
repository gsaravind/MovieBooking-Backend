package com.moviebooking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.Movie;
import com.moviebooking.model.MovieIdentity;
import com.moviebooking.model.Tickets;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.TicketsRepository;
import com.moviebooking.wrapperclasses.MoviePojo;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;
	
	@Mock
	private TicketsRepository ticketsRepository;

	@InjectMocks
	private MovieService movieService;

	private Movie movie;
	
	private Tickets ticket;

	@BeforeEach
	public void setup() {
		MovieIdentity mIdentity = new MovieIdentity("dark", "funbox");
		movie = new Movie(mIdentity, 100);
		ticket = new Tickets();
		ticket.setMovieName("dark");
		ticket.setTheatreName("funbox");
		ticket.setNoOfTicketsBooked(10);
		ticket.setSeatNo(Arrays.asList("A1"));
		ticket.setTicketUpdateStatus(true);
	}

	@DisplayName("JUnit test for init movie method")
	@Test
	public void initMovieTest() {
		given(movieRepository.save(any(Movie.class))).willReturn(movie);
		movieService.initMovie();
		assertThat(movie).isNotNull();
	}

	@DisplayName("JUnit test for get all movies method")
	@Test
	public void testGetAllMovies() {
		List<Movie> lst = Arrays.asList(movie);
		Iterable<Movie> myIterable = lst::iterator;
		given(movieRepository.findAll()).willReturn(myIterable);
		List<MoviePojo> lst1 = movieService.getAllMovies();
		assertEquals(lst.get(0).getMovieIdentity(), lst1.get(0).getMovieIdentity());
	}
	
	@DisplayName("JUnit test for get all movies method with tickets present")
	@Test
	public void testGetAllMoviesWithTicketsPresent() {
		given(ticketsRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(ticket));
		List<Movie> lst = Arrays.asList(movie);
		Iterable<Movie> myIterable = lst::iterator;
		given(movieRepository.findAll()).willReturn(myIterable);
		List<MoviePojo> lst1 = movieService.getAllMovies();
		assertEquals(lst.get(0).getMovieIdentity(), lst1.get(0).getMovieIdentity());
	}

	@DisplayName("JUnit test for search movie method")
	@Test
	public void testSearchMovie() throws CustomException {
		given(ticketsRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(ticket));
		List<Movie> lst = Arrays.asList(movie);
		Iterable<Movie> myIterable = lst::iterator;
		given(movieRepository.findAll()).willReturn(myIterable);
		List<MoviePojo> movie1 = movieService.searchMovie("dark");
		assertThat(movie1).isNotNull();
	}

	@DisplayName("JUnit test for search movie method")
	@Test
	public void testSearchMovieThrowsCustomException() throws CustomException {
		List<Movie> lst = Arrays.asList(movie);
		Iterable<Movie> myIterable = lst::iterator;
		given(movieRepository.findAll()).willReturn(myIterable);
		Assertions.assertThrows(CustomException.class, () -> movieService.searchMovie("darkk"));
	}

	@DisplayName("JUnit test for delete movie method")
	@Test
	public void testDeleteMovie() {
		List<Movie> lst = Arrays.asList(movie);
		Iterable<Movie> myIterable = lst::iterator;
		given(movieRepository.findAll()).willReturn(myIterable);
		Assertions.assertDoesNotThrow(() -> movieService.deleteMovie("dark"));
	}

	@DisplayName("JUnit test for delete movie method throws exception")
	@Test
	public void testDeleteMovieThrowsException() {
		List<Movie> lst = Arrays.asList(movie);
		Iterable<Movie> myIterable = lst::iterator;
		given(movieRepository.findAll()).willReturn(myIterable);
		Assertions.assertThrows(CustomException.class, () -> movieService.deleteMovie("darkk"));
	}
	
	@DisplayName("JUnit test for add movie method")
	@Test
	public void testAddMovie() {
		given(movieRepository.existsById(any(MovieIdentity.class))).willReturn(false);
		Assertions.assertDoesNotThrow(() -> movieService.addMovie(movie));
	}
}
