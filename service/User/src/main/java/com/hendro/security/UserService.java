package com.hendro.service;

import com.hendro.dto.LoginRequest;
import com.hendro.dto.RegisterRequest;
import com.hendro.dto.UserResponse;
import com.hendro.mapper.UserMapper;
import com.hendro.model.User;
import com.hendro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {
        // Check if username exists
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }

        // Map request to entity and encode password
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user to repository
        User savedUser = userRepository.save(user);

        // Return response DTO
        return userMapper.toResponse(savedUser);
    }

    public String login(LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Check if password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // In a real scenario, return JWT or token
        return "Login successful!";
    }
}
