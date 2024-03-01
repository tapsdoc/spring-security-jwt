package com.example.Spring.SecurityAlibou.service.auth;

import lombok.Builder;

@Builder
public record AuthResponse(
    String accessToken,
    String refreshToken
) {
}
