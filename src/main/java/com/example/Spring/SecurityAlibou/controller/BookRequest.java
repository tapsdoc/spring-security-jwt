package com.example.Spring.SecurityAlibou.controller;

import jakarta.validation.constraints.NotEmpty;

public record BookRequest(
    @NotEmpty
    String title,
    @NotEmpty
    String author
) {
}
