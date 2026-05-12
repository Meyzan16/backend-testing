package com.example.notification_service.notif.controller;

import com.example.notification_service.notif.DTO.NotificationEvent;
import com.example.notification_service.notif.Interface.Services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    @PostMapping("/process")
    public ResponseEntity<?> process(@RequestBody NotificationEvent event) {
        notificationService.processNotification(event);

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Notification processed"
                )
        );
    }


}
