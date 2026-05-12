package com.example.auth_service.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
