package com.example.auth_service.shared.Exception;

import java.util.Map;


public class NotifException extends RuntimeException {

    private final Map<String, String> errors;


    public NotifException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }


    public static class StatusPublishException extends NotifException {
        public StatusPublishException(String field, String errorMessage) {
            super(
                    "Login failed",
                    Map.of(field, errorMessage)
            );
        }
    }


}
