package com.example.Spring.SecurityAlibou.service.auth;

import com.example.Spring.SecurityAlibou.exception.domain.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {

    AuthResponse register(RegistrationRequest request);
    AuthResponse login(AuthRequest request) throws UserNotFoundException;
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, UserNotFoundException;
}
