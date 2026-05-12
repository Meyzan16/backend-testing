package com.example.notification_service.notif.Service;

import com.example.notification_service.notif.DTO.NotificationEvent;
import com.example.notification_service.notif.Interface.Repository.NotificationRepository;
import com.example.notification_service.notif.Interface.Services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void processNotification(NotificationEvent event) {
        try {
            System.out.println("Processing notification: " + event.notificationUid());
            System.out.println("Send to: " + event.recipient());

            // Has -a artinya seervice memiliki  interface dalam proses pengirimkan noyifikasi Composition tapi jika di interface namanya Composition

            notificationRepository.updateStatusToSent(event.notificationUid());

            System.out.println("Notification status updated to SENT");

        } catch (Exception ex) {
            notificationRepository.updateStatusToFailed(
                    event.notificationUid(),
                    ex.getMessage()
            );

            throw ex;
        }
    }
}