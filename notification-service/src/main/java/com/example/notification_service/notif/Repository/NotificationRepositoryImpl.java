package com.example.notification_service.notif.Repository;

import com.example.notification_service.notif.Interface.Repository.NotificationRepository;
import com.example.notification_service.shared.Exception.KafkaException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void updateStatusToSent(UUID notificationUid) {

        try {

            String sql = """
                EXEC sp_update_notification_status
                    @p_notification_uid = ?,
                    @p_status = ?,
                    @p_error_message = ?
                """;

            jdbcTemplate.update(
                    sql,
                    notificationUid,
                    "SENT",
                    null
            );

        } catch (DataAccessException ex) {

            throw new KafkaException.KafkaConsumeException(
                    ex.getMostSpecificCause().getMessage()
            );
        }
    }

    @Override
    public void updateStatusToFailed(
            UUID notificationUid,
            String errorMessage
    ) {

        try {

            String sql = """
                EXEC sp_update_notification_status
                    @p_notification_uid = ?,
                    @p_status = ?,
                    @p_error_message = ?
                """;

            jdbcTemplate.update(
                    sql,
                    notificationUid,
                    "FAILED",
                    errorMessage
            );

        } catch (DataAccessException ex) {

            throw new KafkaException.KafkaConsumeException(
                    ex.getMostSpecificCause().getMessage()
            );
        }
    }
}
