package com.tilashar.authservice.service;

import com.tilashar.authservice.model.AuthResponse;
import com.tilashar.authservice.model.AuthRequest;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse authenticate(AuthRequest request);
}
