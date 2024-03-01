package com.example.Spring.SecurityAlibou.controller;

import com.example.Spring.SecurityAlibou.exception.domain.UserNotFoundException;
import com.example.Spring.SecurityAlibou.service.auth.AuthRequest;
import com.example.Spring.SecurityAlibou.service.auth.AuthResponse;
import com.example.Spring.SecurityAlibou.service.auth.AuthService;
import com.example.Spring.SecurityAlibou.service.auth.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")



    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, UserNotFoundException {
        authService.refreshToken(request, response);
    }
}
