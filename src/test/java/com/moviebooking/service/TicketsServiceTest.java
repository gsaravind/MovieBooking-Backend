package com.moviebooking.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviebooking.exception.CustomException;
import com.moviebooking.model.Movie;
import com.moviebooking.model.MovieIdentity;
import com.moviebooking.model.Tickets;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.TicketsRepository;
import com.moviebooking.wrapperclasses.TicketPojo;

@ExtendWith(MockitoExtension.class)
public class TicketsServiceTest {

	@Mock
	private TicketsRepository ticketsRepository;

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private TicketsService ticketsService;

	private TicketPojo ticketPojo;
	private Tickets ticket;
	private Movie movie;

	@BeforeEach
	public void setUp() {
		List<String> seatNo = new ArrayList<>(Arrays.asList("A12", "A13"));
		ticketPojo = new TicketPojo("Dark", "FunBox", 100, seatNo);
		ticket = new Tickets("Dark", "FunBox", 100, new ArrayList<>(seatNo));
		movie = new Movie(new MovieIdentity("Dark", "FunBox"), 100);
	}

	@DisplayName("JUnit test for add ticket method")
	@Test
	public void testAddTicket() {
		given(movieRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(movie));
		given(ticketsRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(ticket));
		given(movieRepository.save(any(Movie.class))).willReturn(movie);
		given(ticketsRepository.save(any(Tickets.class))).willReturn(ticket);
		Assertions.assertDoesNotThrow(() -> ticketsService.addTicket(ticketPojo));
	}

	@DisplayName("JUnit test for add ticket method throw movie information not found exception")
	@Test
	public void testAddTicketThrowsCustomExceptionMovieNotFound() {
		given(movieRepository.findById(any(MovieIdentity.class))).willReturn(Optional.empty());
		Assertions.assertThrows(CustomException.class, () -> ticketsService.addTicket(ticketPojo));
	}

	@DisplayName("JUnit test for add ticket method throw ticket count cannot be zero exception")
	@Test
	public void testAddTicketThrowsCustomExceptionInvalidTicketCount() {
		ticketPojo.setTotNoOfTickets(0);
		given(movieRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(movie));
		Assertions.assertThrows(CustomException.class, () -> ticketsService.addTicket(ticketPojo));
	}

	@DisplayName("JUnit test for add ticket method throw not enough seats exception")
	@Test
	public void testAddTicketThrowsCustomExceptionNotEnoughSeats() {
		movie.setNoOfTickets(0);
		given(movieRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(movie));
		Assertions.assertThrows(CustomException.class, () -> ticketsService.addTicket(ticketPojo));
	}

	@DisplayName("JUnit test for add ticket method ticket not present")
	@Test
	public void testAddTicketTicketNotPresent() {
		given(movieRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(movie));
		given(ticketsRepository.findById(any(MovieIdentity.class))).willReturn(Optional.empty());
		given(movieRepository.save(any(Movie.class))).willReturn(movie);
		given(ticketsRepository.save(any(Tickets.class))).willReturn(ticket);
		Assertions.assertDoesNotThrow(() -> ticketsService.addTicket(ticketPojo));
	}

	@DisplayName("JUnit test for update ticket status method")
	@Test
	public void testUpdateTicketStatus() {
		given(ticketsRepository.findById(any(MovieIdentity.class))).willReturn(Optional.of(ticket));
		given(ticketsRepository.save(any(Tickets.class))).willReturn(ticket);
		Assertions.assertDoesNotThrow(() -> ticketsService.updateTicketStatus(ticketPojo));
	}

	@DisplayName("JUnit test for update ticket status method throws custom exception")
	@Test
	public void testUpdateTicketStatusThrowCustomException() {
		given(ticketsRepository.findById(any(MovieIdentity.class))).willReturn(Optional.empty());
		Assertions.assertThrows(CustomException.class, () -> ticketsService.updateTicketStatus(ticketPojo));
	}

}
