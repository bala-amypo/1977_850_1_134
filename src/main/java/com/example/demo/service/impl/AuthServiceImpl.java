package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;

    public AuthServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void register(RegisterRequest request) {
        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(UserRole.valueOf(request.getRole()))
                .build();

        appUserRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        
        return new AuthResponse("DUMMY_TOKEN");
    }
}

