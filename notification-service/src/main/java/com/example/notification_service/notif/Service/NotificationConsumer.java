package com.example.notification_service.notif.Service;


import com.example.notification_service.notif.DTO.NotificationEvent;
import com.example.notification_service.notif.Interface.Services.NotificationService;
import com.example.notification_service.shared.Exception.KafkaException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotificationService notificationService;

    @KafkaListener(
            topics = "${app.kafka.topic.notification}",
            groupId = "notification-service"
    )
    public void consume(String payload) {
        try {

            System.out.println("Payload : " + payload);

            NotificationEvent event =
                    objectMapper.readValue(
                            payload,
                            NotificationEvent.class
                    );

            notificationService.processNotification(event);

        } catch (Exception ex) {

            throw new KafkaException.KafkaConsumeException(
                    ex.getMessage()
            );
        }
    }
}
