package com.example.notification_service.notif.Interface.Services;

import com.example.notification_service.notif.DTO.NotificationEvent;

public interface NotificationService {
    void processNotification(NotificationEvent event);
}
