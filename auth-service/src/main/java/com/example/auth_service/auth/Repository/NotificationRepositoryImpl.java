package com.example.auth_service.auth.Repository;

import com.example.auth_service.auth.domain.Entity.User;
import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.dto.Event.NotificationEvent;
import com.example.auth_service.auth.interfaces.repository.NotificationRepository;
import com.example.auth_service.shared.Exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Uid insertPendingNotification(NotificationEvent event) {
        try {
            String sql = """
            EXEC sp_insert_notification
                @p_notif_uid = ?,
                @p_user_uid = ?,
                @p_channel = ?,
                @p_recipient = ?,
                @p_subject = ?,
                @p_body = ?
            """;

            jdbcTemplate.update(
                    sql,
                    event.notificationUid().getValue(),
                    event.userUid().getValue(),
                    event.channel(),
                    event.recipient(),
                    event.subject(),
                    event.body()
            );

            return event.notificationUid();

        } catch (DataAccessException ex) {
            throw new AuthException.RegistrationException(
                    "database",
                    ex.getMostSpecificCause().getMessage()
            );
        }
    }
}