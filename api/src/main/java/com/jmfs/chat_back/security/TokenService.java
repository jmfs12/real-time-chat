package com.jmfs.chat_back.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jmfs.chat_back.domain.User;
import com.jmfs.chat_back.exception.TokenCreationException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
public class TokenService {
    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        try {
            log.info("[TOKEN] Generating token for user: {}", user.getEmail());
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            String token = JWT.create()
                    .withIssuer("chat-back-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
            log.info("[TOKEN] Token generated successfully for user: {}", user.getEmail());
            return token;
        } catch (JWTCreationException exception) {
            log.error("[TOKEN] Error generating token for user: {}", user.getEmail(), exception);
            throw new TokenCreationException("Error generating JWT token");
        }
    }

    public String validateToken(String token){
        try {
            log.info("[TOKEN] Validating token");
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            log.info("[TOKEN] Token validation successful");
            return JWT.require(algorithm)
                    .withIssuer("chat-back-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            log.error("[TOKEN] Invalid token", e);
            return null;
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-3"));
    }

}
