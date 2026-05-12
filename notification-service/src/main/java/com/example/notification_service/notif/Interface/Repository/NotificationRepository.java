package com.example.notification_service.notif.Interface.Repository;

import com.example.notification_service.notif.Domain.ValueObject.Uid;

import java.util.UUID;

public interface NotificationRepository {

    void updateStatusToSent(Uid notificationUid);

    void updateStatusToFailed(Uid notificationUid, String errorMessage);

}
