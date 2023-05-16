package com.moviebooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.Movie;
import com.moviebooking.service.MovieService;
import com.moviebooking.wrapperclasses.MoviePojo;
import com.moviebooking.wrapperclasses.SimpleResponse;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("api/v1.0/moviebooking")
@CrossOrigin
public class MovieController {

	static final Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	@PostConstruct
	public void initRoleAndUser() {
		log.info("All movies initialized successfully");
		movieService.initMovie();
	}

	@GetMapping("/all")
	public List<MoviePojo> getAllMovies() {
		log.info("Successfully returing the list of all movies");
		return movieService.getAllMovies();
	}

	@GetMapping("/movies/search/{movieName}")
	public List<MoviePojo> searchMovie(@PathVariable String movieName) throws CustomException {
		List<MoviePojo> movie = movieService.searchMovie(movieName);
		log.info("Movie found successfully: {}", movie);
		return movie;
	}

	@DeleteMapping("/{movieName}/delete")
	public SimpleResponse deleteMovie(@PathVariable String movieName) throws CustomException {
		SimpleResponse response = movieService.deleteMovie(movieName);
		log.info("Movie with movie name {} deleted successfully", movieName);
		return response;
	}
	
	@PostMapping("/addMovie")
	public SimpleResponse addMovie(@RequestBody Movie movie) throws CustomException {
		SimpleResponse response = movieService.addMovie(movie);
		log.info("Movie with movie name {} added successfully", movie.getMovieIdentity().getMovieName());
		return response;
	}
}
