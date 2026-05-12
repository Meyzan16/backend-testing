package com.example.auth_service.auth.Repository;

import com.example.auth_service.auth.domain.Entity.User;
import com.example.auth_service.auth.domain.ValueObject.Email;
import com.example.auth_service.auth.domain.ValueObject.Password;
import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.domain.ValueObject.UserRole;
import com.example.auth_service.auth.interfaces.repository.UserRepositoryInterface;
import com.example.auth_service.shared.Exception.AuthException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        try {
            String sql = """
            EXEC sp_find_user_by_email
                @p_email = ?
            """;

            List<User> users = jdbcTemplate.query(
                    sql,
                    new Object[]{email.getValue()},
                    new UserFindByEmailRowMapper()
            );

            return users.stream().findFirst();
        } catch (DataAccessException ex) {
            throw new AuthException.RegistrationException("database", ex.getMostSpecificCause().getMessage());
        }
    }


    @Override
    public User register(Uid uid,  Email email, String passwordHash, String fullName , String role, Boolean active)
    {
        // SQL Server: EXEC procedure @param1 = ?, @param2 = ?
        String sql = "EXEC sp_register_user @p_user_uid = ?, @p_email = ?, @p_password_hash = ?, @p_full_name = ?, @p_role = ?, @p_is_active = ?";

        try {
            User user = jdbcTemplate.queryForObject(
                    sql,
                    new UserRegisterRowMapper(passwordHash),
                    uid.getValue().toString(),   // 1
                    email.getValue(),            // 2
                    passwordHash,                // 3
                    fullName,                    // 4
                    role,                        // 5
                    active                       // 6
            );

            if (user == null) {
                throw new AuthException.RegistrationException("database", "Register failed: no data returned");
            }

            return user;

        } catch (DataAccessException ex) {
            throw new AuthException.RegistrationException("database", ex.getMostSpecificCause().getMessage());
        }


    }


    private static class UserRegisterRowMapper implements RowMapper<User>
    {

        private final String passwordHash;

        UserRegisterRowMapper(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .uid(Uid.of(UUID.fromString(rs.getString("out_user_uid"))))
                    .email(Email.of(rs.getString("out_email")))
                    .password(Password.fromHash(passwordHash))
                    .fullName(rs.getString("out_full_name"))
                    .role(UserRole.fromString(rs.getString("out_role")))
                    .createdAt(rs.getTimestamp("out_created_at").toLocalDateTime())
                    .build();
        }
    }

    private static class UserFindByEmailRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .id(rs.getLong("out_id"))
                    .uid(Uid.of(UUID.fromString(rs.getString("out_user_uid"))))
                    .email(Email.of(rs.getString("out_email")))
                    .password(Password.fromHash(rs.getString("out_password_hash")))
                    .fullName(rs.getString("out_full_name"))
                    .role(UserRole.fromString(rs.getString("out_role")))
                    .active(rs.getBoolean("out_is_active"))
                    .build();
        }
    }



}
