package com.example.auth_service.auth.domain.ValueObject;

import com.example.auth_service.shared.Exception.NotifException;

public enum StatusNotification {

    RETRY("RETRY"),
    FAILED("FAILED"),
    SENT("SENT"),
    PENDING("PENDING");

    private final String value;

    StatusNotification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatusNotification fromString(String status) {
        for (StatusNotification s : values()) {
            if (s.value.equalsIgnoreCase(status)) {
                return s;
            }
        }

        throw new NotifException.StatusPublishException(
                "status",
                "Unknown notification status: " + status
        );
    }
}