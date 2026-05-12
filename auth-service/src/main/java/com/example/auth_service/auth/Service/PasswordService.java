package com.example.auth_service.auth.Service;

import com.example.auth_service.shared.Exception.AuthException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String hash(String rawPassword) {
        if (rawPassword.length() < 8) {
            throw new AuthException.RegistrationException(
                    "password",
                    "Password must be at least 8 characters"
            );
        }
        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
