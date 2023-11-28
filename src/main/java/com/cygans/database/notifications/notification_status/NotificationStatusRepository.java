package com.cygans.database.notifications.notification_status;

import org.springframework.data.repository.CrudRepository;

public interface NotificationStatusRepository extends CrudRepository<NotificationStatus, Long> {
    NotificationStatus findNotificationStatusByStatus(String status);
    NotificationStatus findNotificationStatusById(Long id);
    @Override
    <S extends NotificationStatus> S save(S s);
}
