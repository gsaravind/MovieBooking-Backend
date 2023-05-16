package com.moviebooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.exception.CustomException;
import com.moviebooking.service.TicketsService;
import com.moviebooking.wrapperclasses.SimpleResponse;
import com.moviebooking.wrapperclasses.TicketPojo;

@RestController
@RequestMapping("api/v1.0/moviebooking")
@CrossOrigin
public class TicketsController {

	static final Logger log = LoggerFactory.getLogger(TicketsController.class);

	@Autowired
	private TicketsService ticketsService;

	@PostMapping("/{movieName}/add")
	public SimpleResponse addMovie(@PathVariable String movieName, @RequestBody TicketPojo pojo)
			throws CustomException {
		pojo.setMovieName(movieName);
		SimpleResponse response = ticketsService.addTicket(pojo);
		log.info("Ticket for the movie with info {} added successfully", pojo);
		return response;
	}

	@PutMapping("/{movieName}/update")
	public SimpleResponse updateTicketStatus(@PathVariable String movieName, @RequestBody TicketPojo pojo)
			throws CustomException {
		pojo.setMovieName(movieName);
		SimpleResponse response = ticketsService.updateTicketStatus(pojo);
		log.info("Ticket status for the movie with info {} updated successfully", pojo);
		return response;
	}

}
