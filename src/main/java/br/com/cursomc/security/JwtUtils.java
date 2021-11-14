package br.com.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			var username = claims.getSubject();
			var expirationDate = claims.getExpiration();
			var nowDate = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null
					&& nowDate.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret.getBytes())
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

}
