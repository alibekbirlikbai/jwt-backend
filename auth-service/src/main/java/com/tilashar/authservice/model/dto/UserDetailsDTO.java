package com.tilashar.authservice.model.dto;

import com.tilashar.authservice.model.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserDetailsDTO implements UserDetails {
    private RegisterRequest registerRequest;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = registerRequest.getRole();
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return registerRequest.getPassword();
    }

    @Override
    public String getUsername() {
        return registerRequest.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Логика проверки срока действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Логика проверки блокировки аккаунта
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Логика проверки срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Логика проверки активности аккаунта
    }
}
