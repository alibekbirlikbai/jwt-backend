package com.tilashar.authservice.controller;

import com.tilashar.authservice.model.AuthResponse;
import com.tilashar.authservice.model.AuthRequest;
import com.tilashar.authservice.service.implement.AuthServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-service/api/auth")
public class AuthController {
    private final AuthServiceImplement authService;

    @Autowired
    public AuthController(AuthServiceImplement authService) {
        this.authService = authService;
    }

    // Регистрация + Авторизация
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }
}
