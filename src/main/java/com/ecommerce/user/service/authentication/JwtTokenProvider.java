package com.ecommerce.user.service.authentication;

import com.bastiaanjansen.jwt.JWT;
import com.bastiaanjansen.jwt.algorithms.Algorithm;
import com.bastiaanjansen.jwt.exceptions.JWTCreationException;
import com.bastiaanjansen.jwt.exceptions.JWTDecodeException;
import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${security.jwt.expiration}")
    private long jwtExpirationInMs;

    private final Algorithm algorithm;

    public JwtTokenProvider() {
        this.algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
    }

    public String generateToken(Authentication authentication) throws JWTCreationException {
        User userDetails = (User) authentication.getPrincipal();

        Date issuedDate = new Date();
        Date expiryDate = new Date(issuedDate.getTime() + jwtExpirationInMs);

        return new JWT.Builder(algorithm)
                .withIssuedAt(issuedDate)
                .withExpirationTime(expiryDate)
                .withClaim("username", userDetails.getUsername())
                .sign();
     }

     public boolean validateToken(String token) {
        try {
            JWT jwt = JWT.fromRawJWT(algorithm, token);
            jwt.validate();
            return true;
        } catch (Exception e) {
            log.error("Token validation failed with exception :: ", e);
            return false;
        }
     }

     public String getUserNameFromToken(String token) {
        try {
            JWT jwt = JWT.fromRawJWT(algorithm, token);
            return jwt.getPayload().getClaim("username", String.class);
        } catch (Exception e) {
            log.error("Failed to get username from token with exception :: ", e);
            return null;
        }
     }

}
