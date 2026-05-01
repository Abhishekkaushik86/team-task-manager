package com.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taskmanager.entity.User;
import com.taskmanager.service.UserService;
import com.taskmanager.config.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedUser = userService.login(user.getEmail(), user.getPassword());
        return jwtUtil.generateToken(loggedUser.getEmail());
    }

    @GetMapping("/")
    public String test() {
        return "Auth Working 🚀";
    }
}