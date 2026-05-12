package com.example.notification_service.notif.DTO;

import com.example.notification_service.notif.Domain.ValueObject.Uid;


public record NotificationEvent(
        Uid notificationUid,
        Uid userUid,
        String channel,
        String recipient,
        String subject,
        String body
) {
}
