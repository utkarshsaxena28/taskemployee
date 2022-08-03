package com.task.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
	}
}
