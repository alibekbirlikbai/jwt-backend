package com.tilashar.authservice.service.implement;

import com.tilashar.authservice.external.UserServiceClient;
import com.tilashar.authservice.model.AuthResponse;
import com.tilashar.authservice.model.AuthRequest;
import com.tilashar.authservice.model.dto.UserDetailsDTO;
import com.tilashar.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImplement jwtServiceImplement;
//    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRequest request) {
        // Шифруем пароль
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        return userServiceClient.saveUser(request)
                .flatMap(response -> {
                    // Если пользователь успешно сохраняется, генерируем JWT token
                    String jwtToken = jwtServiceImplement.generateToken(response);
                    return Mono.just(AuthResponse.builder()
                            .token(jwtToken)
                            .build());
                })
                .switchIfEmpty(Mono.error(new RuntimeException("-- User not registered! --"))).block(); // Обработка случая, когда пользователь не был сохранен
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        return userServiceClient.findUser_byEmail(request.getEmail())
                .flatMap(response -> {
                    // Проверка пароля
                    if (!passwordEncoder.matches(request.getPassword(), response.getPassword())) {
                        return Mono.error(new IllegalArgumentException("-- Wrong password --"));
                    }

                    // Если пользователь существует, генерируем JWT токен
                    String jwtToken = jwtServiceImplement.generateToken(response);
                    return Mono.just(AuthResponse.builder()
                            .token(jwtToken)
                            .build());
                })
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("--- User not found ---")))
                .block();
    }








//        System.out.println("\nrequest - " + request);
//
//        Authentication authentication;
//        authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
//
//        System.out.println("authentication - " + authentication);
//        System.out.println("authentication.getPrincipal() - " + authentication.getPrincipal());
//        System.out.println("userDetailsDTO - " + userDetailsDTO);
//
//        return userServiceClient.findUser_byEmail(request.getEmail())
//                .flatMap(user -> {
//                    // Если пользователь с таким email есть в системе, генерируем JWT token
//                    String jwtToken = jwtServiceImplement.generateToken(userDetailsDTO);
//                    System.out.println("jwtToken - " + jwtToken);
//                    return Mono.just(AuthResponse.builder()
//                            .token(jwtToken)
//                            .build());
//                })
//                .switchIfEmpty(Mono.error(new UsernameNotFoundException("--- User not found ---"))).block();
//    }

}
