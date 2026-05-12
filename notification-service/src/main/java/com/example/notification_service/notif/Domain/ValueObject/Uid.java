package com.example.notification_service.notif.Domain.ValueObject;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;


public final class Uid {

    private final UUID value;

    private Uid(UUID value) {
        this.value = Objects.requireNonNull(value, "UID cannot be null");
    }

    @JsonCreator
    public static Uid of(@JsonProperty("value") UUID value) {
        return new Uid(value);
    }

    public static Uid generate() {
        return new Uid(UUID.randomUUID());
    }

    public static Uid fromString(String value) {
        return new Uid(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }
}
