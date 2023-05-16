package com.moviebooking.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebooking.service.TicketsService;
import com.moviebooking.wrapperclasses.SimpleResponse;
import com.moviebooking.wrapperclasses.TicketPojo;

@ExtendWith(MockitoExtension.class)
public class TicketsControllerTest {

	@Mock
	private TicketsService ticketsService;

	@InjectMocks
	private TicketsController ticketsController;

	@DisplayName("JUnit test for add ticket for a movie method")
	@Test
	public void testAddTicketsForAMovie() throws Exception {
		TicketPojo pojo = new TicketPojo("dark", "funbox", 101, Arrays.asList("A1", "A2"));
		when(ticketsService.addTicket(any(TicketPojo.class)))
				.thenReturn(new SimpleResponse("Ticket booked successfully"));
		String content = (new ObjectMapper()).writeValueAsString(pojo);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/moviebooking/dark/add")
				.contentType(MediaType.APPLICATION_JSON).content(content).header("Authorization", "Bearer ");
		MockMvcBuilders.standaloneSetup(ticketsController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Ticket booked successfully\"}"));
	}

	@DisplayName("JUnit test for update ticket status method")
	@Test
	public void testUpdateTicketStatus() throws Exception {
		TicketPojo pojo = new TicketPojo("dark", "funbox", 101, Arrays.asList("A1", "A2"));
		when(ticketsService.updateTicketStatus(any(TicketPojo.class)))
				.thenReturn(new SimpleResponse("Ticket status updated successfully"));
		String content = (new ObjectMapper()).writeValueAsString(pojo);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1.0/moviebooking/dark/update")
				.contentType(MediaType.APPLICATION_JSON).content(content).header("Authorization", "Bearer ");
		MockMvcBuilders.standaloneSetup(ticketsController).build().perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(
						MockMvcResultMatchers.content().string("{\"message\":\"Ticket status updated successfully\"}"));
	}
}
