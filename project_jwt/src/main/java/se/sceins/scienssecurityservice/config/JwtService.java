package se.sceins.scienssecurityservice.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY ="792F413F4428472B4B6250655368566D597133743677397A244326452948404D635166546A576E5A7234753778214125442A472D4A614E645267556B58703273";
	
	private static final Logger log = LoggerFactory.getLogger(JwtService.class);
	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return this.extractClaim(token , Claims::getSubject);
	}
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		    final Claims claims = extractAllClaims(token);
		    return claimsResolver.apply(claims);
		  }
	 
	 public String generateToken(UserDetails userDetails) {
		return this.generateToken(new HashMap<>(), userDetails);
	 }
	 
	 public String generateToken(Map<String, Object> extraClaim, UserDetails userDetails) {
		 return Jwts.builder()
				 .setClaims(extraClaim)
				 .setSubject(userDetails.getUsername())
				 .setIssuedAt(new Date(System.currentTimeMillis()))
				 .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *24))
				 .signWith(getSingInKey(), SignatureAlgorithm.HS256)
				 .compact();
	 }
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token).getBody();
		
	}
	
	public  boolean isTokenValid(String token , UserDetails userDetails) {
		final String username = this.extractUserName(token);
		return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return this.extractExperation(token).before(new Date());
	}

	private Date extractExperation(String token) {
		// TODO Auto-generated method stub
		return this.extractClaim(token, Claims::getExpiration);
	}

	private Key getSingInKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
