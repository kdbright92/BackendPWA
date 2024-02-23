package com.example.BackendPWA;

import com.example.BackendPWA.ModelResp.ErrorResp;
import com.example.BackendPWA.ModelResp.LoginResp;
import com.example.BackendPWA.Security.JwtService;
import com.example.BackendPWA.User.Roles;
import com.example.BackendPWA.User.User;
import com.example.BackendPWA.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Base64;

@CrossOrigin("")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtUtil;




    @PostMapping ("/login")
    public ResponseEntity login(@RequestBody User user) {

        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                );
                String username = authentication.getName();
                String token = jwtUtil.generateToken(existingUser);

                LoginResp loginRes = new LoginResp(token, existingUser);

                return ResponseEntity.ok(loginRes);
            } catch (BadCredentialsException e) {
                ErrorResp errorResponse = new ErrorResp(HttpStatus.BAD_REQUEST, "Invalid username or password");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            } catch (Exception e) {
                ErrorResp errorResponse = new ErrorResp(HttpStatus.BAD_REQUEST, e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } else {

            ErrorResp errorResponse = new ErrorResp(HttpStatus.NOT_FOUND, "User is not registered");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user ) {
        User userr =new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();


        if(user.getSelectedFile() != null && !user.getSelectedFile().isEmpty()) {
            byte[] decodedBytes = Base64.getDecoder().decode(user.getSelectedFile());
            user.setProfilePicture(decodedBytes);
        }

        User newUser = userService.saveUser(user);

        if (newUser.getId() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser.getId());

        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User existiert schon");
        }
    }
    }

