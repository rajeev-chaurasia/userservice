package com.ecommerce.user.service.authentication;

import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${security.jwt.expiration}")
    private long jwtExpirationInMs;

    private final Key jwtSecretKey;

    public JwtTokenProvider() {
        this.jwtSecretKey = Keys.hmacShaKeyFor(System.getenv("JWT_SECRET").getBytes());
    }

    public String generateToken(Authentication authentication) {
        String userEmail = (String) authentication.getPrincipal();

        Date issuedDate = new Date();
        Date expiryDate = new Date(issuedDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(issuedDate)
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
     }

     public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token validation failed with exception :: ", e);
            throw new TBaseRuntimeException(TBaseError.invalidUserToken);
        }
     }

}
