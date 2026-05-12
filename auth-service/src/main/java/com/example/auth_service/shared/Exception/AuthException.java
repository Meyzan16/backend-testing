package com.example.auth_service.shared.Exception;

import java.util.Map;

public class AuthException extends RuntimeException {

    private final Map<String, String> errors;

    public AuthException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public static class RegistrationException extends AuthException {
        public RegistrationException(String field, String errorMessage) {
            super(
                    "Registration failed",
                    Map.of(field, errorMessage)
            );
        }
    }

    public static class LoginException extends AuthException {
        public LoginException(String field, String errorMessage) {
            super(
                    "Login failed",
                    Map.of(field, errorMessage)
            );
        }
    }

    public static class ValidationException extends AuthException {
        public ValidationException(Map<String, String> errors) {
            super("Failed to validate field", errors);
        }
    }


}