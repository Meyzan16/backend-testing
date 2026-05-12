package com.example.notification_service.shared.Exception;

import java.util.Map;

public class KafkaException extends RuntimeException {

    private final Map<String, String> errors;

    public KafkaException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public static class KafkaPublishException extends KafkaException {
        public KafkaPublishException(String errorMessage) {
            super(
                    "Kafka publish failed",
                    Map.of("kafka", errorMessage)
            );
        }
    }

    public static class KafkaConsumeException extends KafkaException {
        public KafkaConsumeException(String errorMessage) {
            super(
                    "Kafka consume failed",
                    Map.of("kafka", errorMessage)
            );
        }
    }
}