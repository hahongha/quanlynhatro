package com.utc.rental.rental.security.securityv2;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.utc.rental.rental.api.error.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class JwtTokenProvider {

	private String jwtSecret;

	private int jwtExpirationAT;

	private int jwtExpirationRT;

	public JwtTokenProvider(@Value("${app.jwtSecret}") String secretKey, 
			@Value("${app.jwtExpirationAT}") int jwtExpirationAT,
			@Value("${app.jwtExpirationRT}") int jwtExpirationRT
			) {
    }
	
	public Key key() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
	
	
	public String generateAccessToken(Authentication authentication) {

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationAT);

		return Jwts.builder().subject(userPrincipal.getUser_id())
				.issuedAt(new Date())
				.expiration(expiryDate)
				.id(UUID.randomUUID().toString())
				.signWith(key()).compact();

	}

	public String generateRefreshToken(String uid) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationRT);
		
		return Jwts.builder().subject(uid)
				.issuedAt(new Date())
				.expiration(expiryDate)
				.id(UUID.randomUUID().toString())
				.signWith(key()).compact();

	}

	public String getUserIdFromJWT(String token) {

		Claims claims = validateToken(token);
		
		return claims.getSubject().toString();
		
	}

//	public InvalidToken getToken(String token) {
//		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//		InvalidToken invalidToken = new InvalidToken();
//		invalidToken.setId(claims.getId());
//		invalidToken.setExpiryTime(claims.getExpiration());
//		return invalidToken;
//	}

	public Claims validateToken(String authToken) {
		try {
			Claims claims = Jwts.parser()
				    .verifyWith((SecretKey) key()) // Thay vì setSigningKey()
				    .build()
				    .parseSignedClaims(authToken) // Thay thế parseClaimsJws()
				    .getPayload();
			return claims;
		} catch (SignatureException ex) {
			System.out.println("Invalid JWT signature");
			throw new UnauthorizedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
			throw new UnauthorizedException("Invalid JWT signature");
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token");
			throw new UnauthorizedException("Invalid JWT signature");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token");
			throw new UnauthorizedException("Invalid JWT signature");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty.");
			throw new UnauthorizedException("Invalid JWT signature");
		}
	}
}