package com.example.auth_service.auth.Service;

import com.example.auth_service.auth.dto.Event.NotificationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String topic;

    public NotificationProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.topic.notification}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(NotificationEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);

            System.out.println("Publishing Kafka topic: " + topic);
            System.out.println("Kafka payload: " + payload);

            kafkaTemplate.send(topic, event.userUid().toString(), payload)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            System.out.println("Kafka publish failed: " + ex.getMessage());
                        } else {
                            System.out.println("Kafka publish success");
                            System.out.println("Topic: " + result.getRecordMetadata().topic());
                            System.out.println("Partition: " + result.getRecordMetadata().partition());
                            System.out.println("Offset: " + result.getRecordMetadata().offset());
                        }
                    });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to publish notification event", e);
        }
    }
}