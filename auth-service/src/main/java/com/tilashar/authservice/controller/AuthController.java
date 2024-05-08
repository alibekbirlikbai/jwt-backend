package com.tilashar.authservice.controller;

import com.tilashar.authservice.model.AuthenticationRequest;
import com.tilashar.authservice.model.AuthResponse;
import com.tilashar.authservice.model.RegisterRequest;
import com.tilashar.authservice.service.implement.AuthServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-service/api/auth")
public class AuthController {
    private final AuthServiceImplement service;

    @Autowired
    public AuthController(AuthServiceImplement service) {
        this.service = service;
    }

    // Регистрация + Авторизация
}
