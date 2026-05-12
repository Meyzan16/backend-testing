package com.example.notification_service.notif.Interface.Repository;

import java.util.UUID;

public interface NotificationRepository {

    void updateStatusToSent(UUID notificationUid);

    void updateStatusToFailed(UUID notificationUid, String errorMessage);

}
