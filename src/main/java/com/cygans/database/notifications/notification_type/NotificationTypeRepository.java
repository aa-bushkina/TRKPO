package com.cygans.database.notifications.notification_type;

import org.springframework.data.repository.CrudRepository;

public interface NotificationTypeRepository extends CrudRepository<NotificationType, Long> {
    NotificationType findNotificationTypeByType(String type);
    NotificationType findNotificationTypeById(Long id);
    @Override
    <S extends NotificationType> S save(S s);
}
