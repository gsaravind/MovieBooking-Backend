package com.moviebooking.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.moviebooking.service.JwtService;
import com.moviebooking.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, ExpiredJwtException, MalformedJwtException {
		final String header = request.getHeader("Authorization");
		String jwtToken = null;
		String userName = null;
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			try {
				userName = jwtUtil.getUserNameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get token");
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "This Session is expired");
			} catch (MalformedJwtException e) {
				System.out.println("The token is malformed");
				request.setAttribute("malformed", "Malformed Token");
				throw new MalformedJwtException(e.getLocalizedMessage());
			}
		} else {
			System.out.println("Jwt token doesn't start with bearer");
			request.setAttribute("malformed", "Malformed token");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtService.loadUserByUsername(userName);
			if (jwtUtil.validateToken(jwtToken, userDetails, request, response)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
