package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(AuthRequest request) {
        AuthResponse response = new AuthResponse();
        response.setToken("dummy-jwt-token");
        return response;
    }

    @Override
    public void register(RegisterRequest request) {
        // no-op for first review
    }
}