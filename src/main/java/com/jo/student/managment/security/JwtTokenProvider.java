package com.jo.student.managment.security;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private final String secret;

	public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
		this.secret = secret;
	}

	private final long expiration = 5 * 60 * 60 * 1000;
	private final long tokenRefreshThreshold = 5 * 60 * 1000; // 5 minutes

	public String generateToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(System.currentTimeMillis() + expiration);

		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret.getBytes())).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(Base64.getEncoder().encode(secret.getBytes())).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(secret.getBytes())).parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}

	public boolean shouldTokenBeRefreshed(String token) {
		Date expiration = getExpirationDateFromToken(token);
		Date currentTime = new Date();
		long timeToExpiration = expiration.getTime() - currentTime.getTime();

		return timeToExpiration < tokenRefreshThreshold;
	}

	public Date getExpirationDateFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
				.parseClaimsJws(token).getBody();
		return claims.getExpiration();
	}

	public String refreshToken(String token) {
		String username = getUsernameFromToken(token);
		return generateToken(username);
	}
}
