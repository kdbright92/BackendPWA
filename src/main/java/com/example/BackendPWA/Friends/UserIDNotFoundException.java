package com.example.BackendPWA.Friends;

public class UserIDNotFoundException extends RuntimeException  {

           public UserIDNotFoundException(String userId){
            super("User not found with ID: " + userId);
        }
    }
