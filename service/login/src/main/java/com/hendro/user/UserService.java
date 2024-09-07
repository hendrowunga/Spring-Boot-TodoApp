package com.hendro.user;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(UserRegistration userRegistration) throws MessagingException {


    }

    public UserResponse login(UserRequest userRequest) {
        return null;
    }

    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
