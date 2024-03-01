package com.example.Spring.SecurityAlibou.service.auth;

public record AuthRequest(
    String email,
    String password
) {
}
