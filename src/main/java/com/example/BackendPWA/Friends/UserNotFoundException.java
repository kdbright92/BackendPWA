package com.example.BackendPWA.Friends;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long userId) {
        super("User not found with ID: " + userId);
    }
}