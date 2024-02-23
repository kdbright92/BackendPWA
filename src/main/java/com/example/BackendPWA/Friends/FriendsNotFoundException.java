package com.example.BackendPWA.Friends;

public class FriendsNotFoundException extends RuntimeException {
    public FriendsNotFoundException(Long userId) {
        super("Friends not found for user with ID: " + userId);
    }
}