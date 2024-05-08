package com.tilashar.authservice.service.implement;

import com.tilashar.authservice.external.UserServiceClient;
import com.tilashar.authservice.model.AuthenticationRequest;
import com.tilashar.authservice.model.AuthResponse;
import com.tilashar.authservice.model.RegisterRequest;
import com.tilashar.authservice.model.dto.UserDetailsDTO;
import com.tilashar.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImplement jwtServiceImplement;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        return null;
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
