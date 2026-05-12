package com.example.auth_service.auth.domain.ValueObject;

import com.example.auth_service.shared.Exception.AuthException;

public final class Password {

    private final String hashedValue;

    private Password(String hashedValue) {
        if (hashedValue == null || hashedValue.length() < 10) {
            throw new AuthException.RegistrationException(
                    "password",
                    "Invalid password hash"
            );
        }
        this.hashedValue = hashedValue;
    }

    public static Password fromHash(String hash) {
        return new Password(hash);
    }

    public String getHashedValue() {
        return hashedValue;
    }
}
