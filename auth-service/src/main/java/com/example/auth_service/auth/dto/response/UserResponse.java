package com.example.auth_service.auth.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;


public record UserResponse(
        UUID userUid,
        String email,
        String fullName,
        String role,
        LocalDateTime createdAt
) {
}