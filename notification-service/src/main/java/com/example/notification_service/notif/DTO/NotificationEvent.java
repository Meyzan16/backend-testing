package com.example.notification_service.notif.DTO;

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
