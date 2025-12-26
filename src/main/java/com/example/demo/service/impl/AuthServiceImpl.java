package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        appUserRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already registered");
                });

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(UserRole.CLINICIAN)
                .build();

        AppUser saved = appUserRepository.save(user);
        String token = jwtTokenProvider.generateToken(saved);

        AuthResponse response = new AuthResponse();
        response.setEmail(saved.getEmail());
        response.setToken(token);
        response.setUserId(saved.getId());
        response.setRole(saved.getRole());

        return response;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid user"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtTokenProvider.generateToken(user);

        AuthResponse response = new AuthResponse();
        response.setEmail(user.getEmail());
        response.setToken(token);
        response.setUserId(user.getId());
        response.setRole(user.getRole());

        return response;
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }
}
