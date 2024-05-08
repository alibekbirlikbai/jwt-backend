package com.tilashar.userservice.service.implement;

import com.tilashar.userservice.model.Role;
import com.tilashar.userservice.model.User;
import com.tilashar.userservice.repository.UserRepository;
import com.tilashar.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        System.out.println("saved: " + user);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("--- User with this email already exists ---");
        }

        return userRepository.save(user);
    }
}
