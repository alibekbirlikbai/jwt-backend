package com.tilashar.authservice.service;

import com.tilashar.authservice.model.AuthenticationRequest;
import com.tilashar.authservice.model.AuthResponse;
import com.tilashar.authservice.model.RegisterRequest;
import reactor.core.publisher.Mono;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthenticationRequest request);
}
