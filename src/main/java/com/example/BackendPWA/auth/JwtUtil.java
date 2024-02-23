//package com.example.BackendPWA.auth;
//
//import com.example.BackendPWA.User.User;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//@Service
//@RequiredArgsConstructor
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.accessTokenValidity}")
//    private long accessTokenValidity;
//
//    private final String TOKEN_HEADER = "Authorization";
//    private final String TOKEN_PREFIX = "Bearer ";
//
//    public String createToken(User user) {
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + accessTokenValidity);
//
//        return JWT.create()
//                .withSubject(user.getEmail())
//                .withClaim("firstName", user.getName())
//                .withClaim("lastName", user.getLastname())
//                .withExpiresAt(validity)
//                .sign(Algorithm.HMAC256(secretKey));
//    }
//
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(TOKEN_HEADER);
//        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
//            return bearerToken.substring(TOKEN_PREFIX.length());
//        }
//        return null;
//    }
//
//    public DecodedJWT parseJwtClaims(String token) {
//        return JWT.require(Algorithm.HMAC256(secretKey))
//                .build()
//                .verify(token);
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    public String getEmail(String token) {
//        DecodedJWT jwt = parseJwtClaims(token);
//        return jwt.getSubject();
//    }
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//    public Date getExpirationDateFromToken(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public String getUsernameFromToken(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJwt(token)
//                .getBody();
//    }
//
//
//    // Other methods as needed...
//
//}
