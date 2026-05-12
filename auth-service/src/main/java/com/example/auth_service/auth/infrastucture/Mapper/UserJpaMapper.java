package com.example.auth_service.auth.infrastucture.Mapper;

import com.example.auth_service.auth.domain.Entity.User;
import com.example.auth_service.auth.domain.ValueObject.Email;
import com.example.auth_service.auth.domain.ValueObject.Password;
import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.domain.ValueObject.UserRole;
import com.example.auth_service.auth.infrastucture.Entity.UserJpaEntity;

public class UserJpaMapper {

    public static User toDomain(UserJpaEntity entity) {
        return User.builder()
                .id(entity.getId())
                .uid(Uid.of(entity.getUserUid()))
                .email(Email.of(entity.getEmail()))
                .password(Password.fromHash(entity.getPasswordHash()))
                .fullName(entity.getFullName())
                .role(UserRole.valueOf(entity.getRole()))
                .active(Boolean.TRUE.equals(entity.getActive()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
