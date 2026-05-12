package com.example.auth_service.auth.domain.ValueObject;
import com.example.auth_service.shared.Exception.AuthException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;


public final class Email {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final String value;

    private Email(String value) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (value == null || value.isBlank()) {
            throw new AuthException.RegistrationException(
                    "email", "Email cannot be empty"
            );
        }
        String normalized = value.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw new AuthException.RegistrationException(
                    "email", "Invalid email format : " + value
            );
        }
        this.value = normalized;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public String getValue() {
        return value;
    }

    // Domain di email (setelah @)
    public String getDomain() {
        return value.substring(value.indexOf('@') + 1);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Email email)) return false;
//        return value.equals(email.value);
//    }
//
//    @Override
//    public int hashCode() {
//        return value.hashCode();
//    }
//
//    @Override
//    public String toString() {
//        return value;
//    }


}
