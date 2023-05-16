package com.moviebooking.configuration;

import java.io.IOException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.springframework.security.core.AuthenticationException;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException, MalformedJwtException {
		final String expired = (String) request.getAttribute("expired");
		final String malformed = (String) request.getAttribute("malformed");
		if (expired != null) {
			response.sendError(401, expired);
		} else if (malformed != null) {
			response.sendError(498, malformed);
		}
	}
}
