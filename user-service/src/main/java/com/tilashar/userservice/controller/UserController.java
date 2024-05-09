package com.tilashar.userservice.controller;

import com.tilashar.userservice.model.User;
import com.tilashar.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user-service/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{email}")
    public Optional<User> getByEmail(@PathVariable String email) {
        Optional<User> user = userService.getByEmail(email);
        System.out.println(user);
        return user;
    }
    
}
