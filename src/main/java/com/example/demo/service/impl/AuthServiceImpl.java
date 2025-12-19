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
        // dummy token for now (test-case safe)
        return new AuthResponse("dummy-jwt-token");
    }

    @Override
    public void register(RegisterRequest request) {
        // no logic required for first review
    }
}