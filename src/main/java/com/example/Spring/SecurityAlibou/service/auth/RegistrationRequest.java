package com.example.Spring.SecurityAlibou.service.auth;

public record RegistrationRequest (
    String firstName,
    String lastName,
    String email,
    String password
) {
}
