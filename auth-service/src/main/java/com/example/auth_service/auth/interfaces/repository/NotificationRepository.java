package com.example.auth_service.auth.interfaces.repository;

import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.dto.Event.NotificationEvent;

public interface NotificationRepository {
    Uid insertPendingNotification(NotificationEvent event);
}
