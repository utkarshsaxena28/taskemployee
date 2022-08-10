package com.task.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.task.entity.Employee;
import com.task.service.EmployeeService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenHelper.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.error("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenHelper.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePAT =
						new UsernamePasswordAuthenticationToken
								(userDetails, null, userDetails.getAuthorities());
				
				usernamePAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePAT);
			}
		}
		chain.doFilter(request, response);
	}




	//==============================================old========================================================
	/*
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		// Get Token
		String requestToken = request.getHeader("Authorization");
		
		
		System.out.println(requestToken);
		
		String Ename = null;
		
		String token = null;
		
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if(requestToken != null &&  requestToken.startsWith("Bearer"))
		{
			token = requestToken.substring(7);
			
			try
			{
				Ename = this.jwtTokenHelper.getEmployeenameFromToken(token);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("unable to get jwt token");
			}
			catch (ExpiredJwtException ex) 
			{
				System.out.println("unable to get jwt token");
			}
			catch (MalformedJwtException ex) 
			{
				System.out.println("unable to get jwt token");
			}
		}
		else
		{
			System.out.println("JWT do not starts with Bearer");
		}
		
		// Once we get the token validate it.
		if (Ename != null &&  SecurityContextHolder.getContext().getAuthentication()==null) 
		{
			Employee employee = this.employeeService.getEmployeeByName(Ename);
			
			if (this.jwtTokenHelper.validateToken(token, employee)) {
				
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(token, employee);
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
			else {
				System.out.println("Invalid Jwt Token");
			}
		}
		else
		{
			System.out.println("User name is null or context is not null");
		}
	}*/
}
