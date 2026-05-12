package com.example.auth_service.auth.interfaces.repository;

import com.example.auth_service.auth.domain.Entity.User;
import com.example.auth_service.auth.domain.ValueObject.Email;
import com.example.auth_service.auth.domain.ValueObject.Uid;

import java.util.Optional;

public interface UserRepositoryInterface {
    User register(Uid uid, Email email, String passwordHash, String fullName , String role, Boolean active);

    Optional<User> findByEmail(Email email);
}
