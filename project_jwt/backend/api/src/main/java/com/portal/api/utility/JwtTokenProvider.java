package com.portal.api.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.portal.api.constant.SecurityConstant;
import com.portal.api.domain.UserPrinciple;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import static java.util.Arrays.stream;

@Component
public class JwtTokenProvider {

   // @Value("${jwt.secret}")
    private String secret="dsfdsfdsfdsfd";

    public String generateJwtToken(UserPrinciple userPrinciple){
        String[] claims = this.getClaimsFromUser(userPrinciple);
        return JWT.create().withIssuer(SecurityConstant.GET_ARRAY_LLC)
                .withAudience(SecurityConstant.GET_ARRAY_ADMINISTRATION)
                .withIssuedAt(new Date()).withSubject(userPrinciple.getUsername())
                .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConstant.EXPIRATION_TIME) )
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }
    public List<GrantedAuthority> getAuthorities(String token){
        String[] claims = this.getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = this.getJWTVerifier();
        return  verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES).asArray(String.class);
    }

    public Authentication getAuthentication(String userName , List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return  authenticationToken;
    }

    public boolean isTokenValid(String userName, String token){
        JWTVerifier  verifier = this.getJWTVerifier();
        return StringUtils.isNoneEmpty(userName) && !this.isTokenExpired(verifier, token);
    }
    public String getSubject(String token){
        JWTVerifier verifier = this.getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expirationDate = verifier.verify(token).getExpiresAt();
       return expirationDate.before(new Date());
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try{
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_ARRAY_LLC).build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException(SecurityConstant.TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }

    private String[] getClaimsFromUser(UserPrinciple userPrinciple) {
      //  List<String> authorities = new ArrayList<>();
        List<String> authorities= userPrinciple.getAuthorities().stream().map(u-> u.getAuthority()).collect(Collectors.toList());
        return authorities.toArray(new String[0]);
    }
}
