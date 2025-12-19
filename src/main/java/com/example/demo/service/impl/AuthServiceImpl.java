package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(String username, String password) {
        // Dummy token for test case
        return new AuthResponse("dummy-jwt-token");
    }
}
