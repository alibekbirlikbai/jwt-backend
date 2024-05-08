package com.tilashar.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                // Разрешить доступ к POST-запросам на /api/users без аутентификации
                .requestMatchers(request -> "/user-service/api/users".equals(request.getRequestURI()) && "POST".equals(request.getMethod()))
                .permitAll()
                // Для всех остальных запросов требовать аутентификацию
                .anyRequest().authenticated();

        return http.build();
    }
}

