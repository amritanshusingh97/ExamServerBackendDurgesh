package com.exam.examserver.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.examserver.Services.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

// for validating JWT token coming in header with each request for accessing resource/Url
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// having value of authorization passed in header Ex: Bearer ----token----
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("requestTokenHeader :" + requestTokenHeader);

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			// for valid token
			jwtToken = requestTokenHeader.substring(7);

			try {
				// can have runtime exception while fetching username
				username = jwtUtil.extractUsername(jwtToken);

			} catch (ExpiredJwtException e) {
				System.out.println("Token Expired");
				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid Token , token does not starts with bearer ");
		}

		// after extracting token now we will Validate token

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
				// token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}
		} else {
			System.out.println("Token is not valid");
		}

		filterChain.doFilter(request, response);
	}

}
