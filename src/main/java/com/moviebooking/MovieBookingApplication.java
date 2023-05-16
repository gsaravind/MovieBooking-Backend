package com.moviebooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieBookingApplication {

	static final Logger log = LoggerFactory.getLogger(MovieBookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApplication.class, args);
		log.info("Main application started");
	}

}
