package com.example.auth_service.auth.dto.Event;

import com.example.auth_service.auth.domain.ValueObject.Uid;


public record NotificationEvent(
        Uid notificationUid,
        Uid userUid,
        String channel,
        String recipient,
        String subject,
        String body
) {
}
