//package com.example.BackendPWA.Security;
//
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@Slf4j
//@CrossOrigin(origins = "*/*")
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager, String secretKey) {
//        this.authenticationManager = authenticationManager;
//    }
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        String authorizationHeader = request.getHeader("Authorization");
//        String base64;
//        String username = "";
//        String password = "";
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
//            base64 = request.getHeader("Authorization").substring("Basic ".length());
//            try {
//                byte[] decodedBytes = Base64.getDecoder().decode(base64);
//                String decodedString = new String(decodedBytes);
//                username = decodedString.substring(0, decodedString.indexOf(":"));
//                password = decodedString.substring(decodedString.indexOf(":") + 1);
//            }catch (Exception e){
//                username = "";
//                password = "";
//            }
//        }
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        return authenticationManager.authenticate(authenticationToken);
//
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        User user = (User) authentication.getPrincipal();
//
//        Algorithm algorithm = Algorithm.HMAC256("geheim".getBytes());
//        String access_token = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 24 * 60 * 60 * 1000))
//                .withIssuer(request.getRequestURI())
//                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .sign(algorithm);
//
//        response.setHeader("token", "Bearer " + access_token);
//    }
//}
