package com.example.BackendPWA.ModelResp;

import com.example.BackendPWA.User.User;

public class LoginResp {
    private String email;
    private User user;
    private String token;

    public LoginResp( String token, User user) {

        this.token = token;
        this.user=user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
