package com.jmfs.chat_back.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jmfs.chat_back.domain.user.User;
import com.jmfs.chat_back.exceptions.TokenCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            String token = JWT.create()
                    .withIssuer("chat-back-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new TokenCreationException("Error generating JWT token");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer("chat-back-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-3"));
    }

}
