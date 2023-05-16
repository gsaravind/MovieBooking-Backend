package com.moviebooking.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.Movie;
import com.moviebooking.model.MovieIdentity;
import com.moviebooking.model.Tickets;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.TicketsRepository;
import com.moviebooking.wrapperclasses.MoviePojo;
import com.moviebooking.wrapperclasses.SimpleResponse;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketsRepository ticketsRepository;

	static final Logger log = LoggerFactory.getLogger(MovieService.class);

	public void initMovie() {
		Movie dark = new Movie(new MovieIdentity("The Dark", "Inox Theatre"), 48);
		Movie superman = new Movie(new MovieIdentity("The Superman", "FunBox Theatre"), 48);
		Movie smile = new Movie(new MovieIdentity("Smile", "FunBox Theatre"), 48);
		movieRepository.save(dark);
		log.info("Movie with info {} added successfully", dark);
		movieRepository.save(superman);
		log.info("Movie with info {} added successfully", superman);
		movieRepository.save(smile);
		log.info("Movie with info {} added successfully", smile);
	}

	public List<MoviePojo> getAllMovies() {
		List<MoviePojo> res = new ArrayList<>();
		movieRepository.findAll().forEach(e -> {
			MoviePojo ele = new MoviePojo();
			ele.setMovieIdentity(e.getMovieIdentity());
			ele.setNoOfTickets(e.getNoOfTickets());
			Optional<Tickets> tic = ticketsRepository.findById(e.getMovieIdentity());
			if (tic.isPresent()) {
				ele.setBookedSeats(tic.get().getSeatNo());
				ele.setStatusUpdated(tic.get().isTicketUpdateStatus());
			} else {
				ele.setStatusUpdated(true);
			}
			res.add(ele);
		});
		log.info("Found movie: {}", res);
		return res;
	}

	public List<MoviePojo> searchMovie(String movieName) throws CustomException {
		Iterator<Movie> itr = movieRepository.findAll().iterator();
		List<MoviePojo> res = new ArrayList<>();
		while (itr.hasNext()) {
			Movie movie = itr.next();
			String mN = movie.getMovieIdentity().getMovieName();
			if (mN.toLowerCase().contains(movieName.toLowerCase())) {
				MoviePojo ele = new MoviePojo();
				ele.setMovieIdentity(movie.getMovieIdentity());
				ele.setNoOfTickets(movie.getNoOfTickets());
				Optional<Tickets> tic = ticketsRepository.findById(movie.getMovieIdentity());
				if (tic.isPresent()) {
					ele.setBookedSeats(tic.get().getSeatNo());
					ele.setStatusUpdated(tic.get().isTicketUpdateStatus());
				} else {
					ele.setStatusUpdated(true);
				}
				res.add(ele);
			}
		}
		if (res.size() > 0)
			return res;
		log.warn("Unable to find the movie with name {}", movieName);
		throw new CustomException("Unable to find movie with name - " + movieName);
	}

	public SimpleResponse deleteMovie(String movieName) throws CustomException {
		Iterator<Movie> itr = movieRepository.findAll().iterator();
		while (itr.hasNext()) {
			Movie movie = itr.next();
			if (movie.getMovieIdentity().getMovieName().equals(movieName)) {
				movieRepository.deleteById(movie.getMovieIdentity());
				log.info("Movie deleted successfully with identity {}", movie.getMovieIdentity());
				return new SimpleResponse("Movie deleted successfully");
			}
		}
		log.warn("Unable to delete movie - unable to find movie with name {}", movieName);
		throw new CustomException("Unable to find movie with name - " + movieName);
	}

	public SimpleResponse addMovie(Movie movie) throws CustomException {
		if (movieRepository.existsById(movie.getMovieIdentity())) {
			log.warn("Unable to add Movie, already exists with identity {}", movie.getMovieIdentity());
			throw new CustomException("Movie already exists");
		}
		movie.setNoOfTickets(48);
		movieRepository.save(movie);
		log.info("Movie added successfully with identity {}", movie.getMovieIdentity());
		return new SimpleResponse("Movie added successfully");
	}
}
