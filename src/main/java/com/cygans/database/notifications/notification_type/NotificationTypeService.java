package com.cygans.database.notifications.notification_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationTypeService {
    @Autowired
    private NotificationTypeRepository repository;

    public Long getNotificationTypeId(TypeOfNotification type) {
        return repository.findNotificationTypeByType(type.getValue()).getId();
    }

    public String getNotificationTypeType(Long id) {
        return repository.findNotificationTypeById(id).getType();
    }

    public void fill() {
        if (repository.count() == 0) {
            repository.save(new NotificationType(TypeOfNotification.ADD_REQUEST.getValue()));
            repository.save(new NotificationType(TypeOfNotification.DELETE_REQUEST.getValue()));
            repository.save(new NotificationType(TypeOfNotification.QUESTION.getValue()));
            repository.save(new NotificationType(TypeOfNotification.NEW_LOG.getValue()));
            repository.save(new NotificationType(TypeOfNotification.ANSWER_ON_LOG.getValue()));
            repository.save(new NotificationType(TypeOfNotification.ANSWER_ON_QUESTION.getValue()));
            repository.save(new NotificationType(TypeOfNotification.DECLINE_MENTOR.getValue()));
        } else if (repository.count() > 7) {
            System.out.println("Что-то идет не так, почистите таблицу notification_type!!! В ней должно быть только 7 заранее добавленные записи!!!");
        }
    }
}
