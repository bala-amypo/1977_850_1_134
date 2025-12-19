package com.example.demo.dto;

public class AuthResponse {

    private String token;

    // no-arg constructor (REQUIRED)
    public AuthResponse() {
    }

    // parameterized constructor (THIS FIXES THE ERROR)
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}