package br.com.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	@Value("${jwt.SecretKey}")
	private String jwtSecret;

	@Value("${jwt.ExpirationMs}")
	private Integer jwtExpirationMs;

	public String generateJwtToken(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(
						new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
				.compact();
	}

}
