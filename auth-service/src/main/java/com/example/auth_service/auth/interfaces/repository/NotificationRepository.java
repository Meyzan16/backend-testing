package com.example.auth_service.auth.interfaces.repository;

import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.dto.Event.NotificationEvent;

import java.util.UUID;

public interface NotificationRepository {
    UUID insertPendingNotification(NotificationEvent event);
}
