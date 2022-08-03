package com.task.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.task.entity.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY = 5 *60 * 60;
	
	private String secret = "JwtTokenKey";
	
	//reterive employeename from jwt token
	public String getEmployeenameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	//reterive employeename from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> ClaimsResolver) {
		final Claims claim = getAllClaimsFromToken(token);
		return ClaimsResolver.apply(claim);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration =  getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(Employee employee) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, employee.getName());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean validateToken(String token, Employee employee) {
		final String Ename = getEmployeenameFromToken(token);
		return (Ename.equals(employee.getName()) && isTokenExpired(token));
	}
	
	

}

