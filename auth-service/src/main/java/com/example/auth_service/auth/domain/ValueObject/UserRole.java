package com.example.auth_service.auth.domain.ValueObject;

import com.example.auth_service.shared.Exception.AuthException;

public enum UserRole {

        USER("USER"),
        ADMIN("ADMIN");

        private final String value;

        UserRole(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static UserRole fromString(String role) {
            for (UserRole r : values()) {
                if (r.value.equalsIgnoreCase(role)) {
                    return r;
                }
            }
            throw new AuthException.RegistrationException(
                    "Role",
                    "Unknown role: " + role
            );
        }
}
