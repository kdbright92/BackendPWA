package com.example.BackendPWA.Security;


import java.security.Key;
import java.util.*;
import java.util.function.Function;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private final String secretKey = "secret";

    private final long jwtExpiration = 3 * 24 * 60 * 60 * 1000;  // 3 days

    public String generateToken(UserDetails userDetails) {
        return buildToken(userDetails);
    }

    private String buildToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + this.jwtExpiration))
                .sign(getSignInAlgorithm());
    }


    public String extractUsername(String token) {
        return extractPayload(token, DecodedJWT::getSubject);
    }

    public <T> T extractPayload(String token, Function<DecodedJWT, T> payloadResolver) {
        final DecodedJWT claims = getDecodedJWT(token);
        return payloadResolver.apply(claims);
    }

    private DecodedJWT getDecodedJWT(String token) {
        return getJWTVerifier()
                .verify(token);
    }
    private JWTVerifier getJWTVerifier() {
        return JWT.require(getSignInAlgorithm()).build();
    }
    private Algorithm getSignInAlgorithm() {
        return Algorithm.HMAC256(secretKey.getBytes());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails);
    }


}