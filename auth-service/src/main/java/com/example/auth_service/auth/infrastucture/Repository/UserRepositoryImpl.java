package com.example.auth_service.auth.infrastucture.Repository;

import com.example.auth_service.auth.domain.Entity.User;
import com.example.auth_service.auth.domain.ValueObject.Email;
import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.infrastucture.Entity.UserJpaEntity;
import com.example.auth_service.auth.infrastucture.Mapper.UserJpaMapper;
import com.example.auth_service.auth.interfaces.repository.UserRepositoryInterface;
import com.example.auth_service.shared.Exception.AuthException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryInterface {

    private final EntityManager entityManager;

    @Override
    public User register(
            Uid uid,
            Email email,
            String passwordHash,
            String fullName,
            String role,
            Boolean active
    ) {
        try {
            List<UserJpaEntity> users = entityManager
                    .createNativeQuery(
                            """
                            EXEC sp_register_user
                                @p_user_uid = :userUid,
                                @p_email = :email,
                                @p_password_hash = :passwordHash,
                                @p_full_name = :fullName,
                                @p_role = :role,
                                @p_is_active = :active
                            """,
                            UserJpaEntity.class
                    )
                    .setParameter("userUid", uid.getValue())
                    .setParameter("email", email.getValue())
                    .setParameter("passwordHash", passwordHash)
                    .setParameter("fullName", fullName)
                    .setParameter("role", role)
                    .setParameter("active", active)
                    .getResultList();

            return users.stream()
                    .findFirst()
                    .map(UserJpaMapper::toDomain)
                    .orElseThrow(() -> new AuthException.RegistrationException(
                            "database",
                            "Register failed: no data returned"
                    ));

        } catch (PersistenceException ex) {
            throw new AuthException.RegistrationException(
                    "database",
                    ex.getMessage()
            );
        }
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        try {
            List<UserJpaEntity> users = entityManager
                    .createNativeQuery(
                            """
                            EXEC sp_find_user_by_email
                                @p_email = :email
                            """,
                            UserJpaEntity.class
                    )
                    .setParameter("email", email.getValue())
                    .getResultList();

            return users.stream()
                    .findFirst()
                    .map(UserJpaMapper::toDomain);

        } catch (PersistenceException ex) {
            throw new AuthException.RegistrationException(
                    "database",
                    ex.getMessage()
            );
        }
    }
}