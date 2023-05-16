package com.moviebooking.service;

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
import com.moviebooking.wrapperclasses.SimpleResponse;
import com.moviebooking.wrapperclasses.TicketPojo;

@Service
public class TicketsService {

	static final Logger log = LoggerFactory.getLogger(TicketsService.class);

	@Autowired
	private TicketsRepository ticketRepository;

	@Autowired
	private MovieRepository movieRepository;

	public SimpleResponse addTicket(TicketPojo ticketPojo) throws CustomException {
		Tickets tickets = new Tickets(ticketPojo.getMovieName(), ticketPojo.getTheatreName(),
				ticketPojo.getTotNoOfTickets(), ticketPojo.getSeatNo());
		MovieIdentity identity = new MovieIdentity(ticketPojo.getMovieName(), ticketPojo.getTheatreName());
		Optional<Movie> opMovie = movieRepository.findById(identity);
		if (opMovie.isEmpty()) {
			log.warn("Movie information not found in the database for the request {}", ticketPojo);
			throw new CustomException("Movie information not found");
		}
		Movie movie = opMovie.get();
		int totTickets = movie.getNoOfTickets();
		if (ticketPojo.getTotNoOfTickets() == 0) {
			log.warn("Ticket count can't be zero for the request {}", ticketPojo);
			throw new CustomException("Tickets count can't be zero");
		}
		if (totTickets < ticketPojo.getTotNoOfTickets()) {
			log.warn("Not enough seats are available for the request {}", ticketPojo);
			throw new CustomException("Not enough seats are available");
		}
		movie.setNoOfTickets(totTickets - ticketPojo.getTotNoOfTickets());
		Optional<Tickets> opTicket = ticketRepository.findById(identity);
		if (opTicket.isPresent()) {
			tickets.setNoOfTicketsBooked(ticketPojo.getTotNoOfTickets() + opTicket.get().getNoOfTicketsBooked());
			List<String> vals = opTicket.get().getSeatNo();
			tickets.getSeatNo().addAll(vals);
		}
		tickets.setTicketUpdateStatus(false);
		movieRepository.save(movie);
		ticketRepository.save(tickets);
		log.info("Tickets booked successfully for the request {}", ticketPojo);
		return new SimpleResponse("Movie tickets booked successfully");
	}

	public SimpleResponse updateTicketStatus(TicketPojo ticketPojo) throws CustomException {
		MovieIdentity identity = new MovieIdentity(ticketPojo.getMovieName(), ticketPojo.getTheatreName());
		Optional<Tickets> opTickets = ticketRepository.findById(identity);
		if (opTickets.isEmpty()) {
			log.warn("Unable to update status -> Ticket information not found for the request {}", ticketPojo);
			throw new CustomException("Ticket information not found");
		}
		Tickets ticket = opTickets.get();
		ticket.setTicketUpdateStatus(true);
		ticketRepository.save(ticket);
		log.info("Ticket status updated successfully for the request {}", ticketPojo);
		return new SimpleResponse("Ticket status updated successfully");
	}
}
