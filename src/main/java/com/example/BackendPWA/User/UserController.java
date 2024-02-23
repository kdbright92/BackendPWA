package com.example.BackendPWA.User;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController

@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllProfiles")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,
                                        @RequestBody Map<String, String> request)
    {

        String newUsername = request.get("newUsername");
        String newPassword = request.get("newPassword");
        String selectedFile = request.get("selectedFile");
        String newCity = request.get("newCity");
        String newCountry = request.get("newCountry");



        // Update the user
        try {
            User updatedUser = userService.updateUser(userId, newUsername,newPassword,newCountry, newCity,selectedFile);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public User getUser( @PathVariable long id){
        return userService.getUserById(id);

    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getImage/{userId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long userId) {

        byte[] imageData = userService.getImageData(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
