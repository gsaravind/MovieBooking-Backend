package com.moviebooking.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.moviebooking.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;

	@Test
	public void testBadCredentialsExceptionHandler() {
		BadCredentialsException exception = new BadCredentialsException("Invalid Credentials");
		ResponseEntity<com.moviebooking.exception.AuthenticationException> response = globalExceptionHandler
				.badCredentialsExceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid Credentials", response.getBody().getMessage());
	}

	@Test
	public void testDisabledUserExceptionHandler() {
		UsernameNotFoundException exception = new UsernameNotFoundException("Username not found");
		ResponseEntity<com.moviebooking.exception.AuthenticationException> response = globalExceptionHandler
				.disabledUserExceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Username not found", response.getBody().getMessage());
	}

	@Test
	public void testCustomExceptionHandler() {
		CustomException exception = new CustomException("Custom error message");
		ResponseEntity<com.moviebooking.exception.AuthenticationException> response = globalExceptionHandler
				.disabledUserExceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Custom error message", response.getBody().getMessage());
	}

	@Test
	public void testExpiredJwtExceptionHandler() {
		ExpiredJwtException exception = new ExpiredJwtException(null, null, "This Session is expired");
		ResponseEntity<com.moviebooking.exception.AuthenticationException> response = globalExceptionHandler
				.expiredJwtExceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("This Session is expired", response.getBody().getMessage());
	}

	@Test
	public void testHandleNullValueException() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		User user = new User();
		user.setContactNumber(null);
		Set<ConstraintViolation<User>> result = validator.validate(user);
		ConstraintViolationException exception = new ConstraintViolationException(result);
		ResponseEntity<com.moviebooking.exception.AuthenticationException> response = globalExceptionHandler
				.handleNullValueException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void testHandleMalformedTokenException() {
		MalformedJwtException exception = new MalformedJwtException("The Provided token is malformed");
		ResponseEntity<com.moviebooking.exception.AuthenticationException> response = globalExceptionHandler
				.handleMalformedTokenException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("The Provided token is malformed", response.getBody().getMessage());
	}
}
