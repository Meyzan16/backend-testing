package com.example.auth_service.auth.dto.Event;

import java.util.UUID;


public record NotificationEvent(
        UUID notificationUid,
        UUID userUid,
        String channel,
        String recipient,
        String subject,
        String body
) {
}
