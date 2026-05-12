package com.example.auth_service.auth.domain.ValueObject;

import com.example.auth_service.shared.Exception.AuthException;

import java.util.Objects;
import java.util.UUID;


public final class Uid {

    private final UUID value;

    private Uid(UUID value) {
        this.value = Objects.requireNonNull(value, "UID cannot be null");
    }

    public static Uid of(UUID value) {
        return new Uid(value);
    }

    public static Uid generate() {
        return new Uid(UUID.randomUUID());
    }

    public static Uid fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new AuthException.RegistrationException(
                    "UID",
                    "UID string cannot be empty"
            );
        }
        return new Uid(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }
}
