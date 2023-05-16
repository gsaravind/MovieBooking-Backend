package com.moviebooking.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebooking.model.Movie;
import com.moviebooking.model.MovieIdentity;
import com.moviebooking.service.MovieService;
import com.moviebooking.wrapperclasses.MoviePojo;
import com.moviebooking.wrapperclasses.SimpleResponse;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

	@Mock
	private MovieService movieService;

	@InjectMocks
	private MovieController movieController;

	@DisplayName("JUnit test for init movie method")
	@Test
	public void testInitMovie() {
		doNothing().when(movieService).initMovie();
		Assertions.assertDoesNotThrow(() -> movieController.initRoleAndUser());
	}

	@DisplayName("JUnit test for get all movies method")
	@Test
	public void testGetAllMovies() throws Exception {
		MoviePojo movie = new MoviePojo(new MovieIdentity("dark", "funbox"), 101, new ArrayList<>());
		List<MoviePojo> lst = Arrays.asList(movie);
		when(movieService.getAllMovies()).thenReturn(lst);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/moviebooking/all");
		MockMvcBuilders.standaloneSetup(movieController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string(
						"[{\"movieIdentity\":{\"movieName\":\"dark\",\"theatreName\":\"funbox\"},\"noOfTickets\":101,\"bookedSeats\":[],\"statusUpdated\":false}]"));
	}

	@DisplayName("JUnit test for search movie method")
	@Test
	public void testSearchMovie() throws Exception {
		MoviePojo movie = new MoviePojo(new MovieIdentity("dark", "funbox"), 101, new ArrayList<>());
		List<MoviePojo> res = new ArrayList<>();
		res.add(movie);
		when(movieService.searchMovie("dark")).thenReturn(res);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/v1.0/moviebooking/movies/search/dark");
		MockMvcBuilders.standaloneSetup(movieController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string(
						"[{\"movieIdentity\":{\"movieName\":\"dark\",\"theatreName\":\"funbox\"},\"noOfTickets\":101,\"bookedSeats\":[],\"statusUpdated\":false}]"));
	}

	@DisplayName("JUnit test for delete movie method")
	@Test
	public void testDeleteMovie() throws Exception {
		when(movieService.deleteMovie("dark")).thenReturn(new SimpleResponse("Movie deleted successfully"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/api/v1.0/moviebooking/dark/delete").header("Authorization", "Bearer ");
		MockMvcBuilders.standaloneSetup(movieController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string(
						"{\"message\":\"Movie deleted successfully\"}"));
	}

	@DisplayName("JUnit test for add movie method")
	@Test
	public void testAddMovie() throws Exception {
		Movie pojo = new Movie(new MovieIdentity("Dark", "FunBox"), 101);
		String content = (new ObjectMapper()).writeValueAsString(pojo);
		when(movieService.addMovie(any(Movie.class))).thenReturn(new SimpleResponse("Movie added successfully"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/moviebooking/addMovie")
				.contentType(MediaType.APPLICATION_JSON).content(content).header("Authorization", "Bearer ");
		MockMvcBuilders.standaloneSetup(movieController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Movie added successfully\"}"));
	}

}
