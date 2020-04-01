package com.icon.testeWsSpringBoot.model.security;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.icon.testeWsSpringBoot.exception.BadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:3600000}")
	private int validityInMilliseconds = 3600000; // 1h

	@Autowired
	private UserDetailsService uds;

	@PostConstruct
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username, List<String> roles) {

		Claims cc = Jwts.claims().setSubject(username);
		cc.put("roles", roles);
		Date now = new Date();
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MILLISECOND, validityInMilliseconds);
		Date validity = c.getTime();

		return Jwts.builder().setClaims(cc).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails u = this.uds.loadUserByUsername(getUsername(token));

		return new UsernamePasswordAuthenticationToken(u, "", u.getAuthorities());

	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {

			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}

			return true;
		} catch (Exception e) {
			throw new BadRequestException("Token invalido ou expirado!");
		}

	}
}
