package com.root.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.root.exceptions.TaskAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-millisenconds}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date actualDate = new Date();
		Date expirationDate = new Date(actualDate.getTime() + jwtExpirationInMs);

		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;
	}

	public String obtainUsernameForJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new TaskAppException(HttpStatus.BAD_REQUEST, "JWT sign not valid");
		} catch (MalformedJwtException ex) {
			throw new TaskAppException(HttpStatus.BAD_REQUEST, "JWT not valid");
		} catch (ExpiredJwtException ex) {
			throw new TaskAppException(HttpStatus.BAD_REQUEST, "JWT token get out of time");
		} catch (UnsupportedJwtException ex) {
			throw new TaskAppException(HttpStatus.BAD_REQUEST, "JWT thoken not compatible");
		} catch (IllegalArgumentException ex) {
			throw new TaskAppException(HttpStatus.BAD_REQUEST, "JWT token claims not valid");
		}
	}
}
