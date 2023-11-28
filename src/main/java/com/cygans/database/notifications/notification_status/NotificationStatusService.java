package com.cygans.database.notifications.notification_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationStatusService {
    @Autowired
    private NotificationStatusRepository repository;

    public Long getNotificationStatusId(StatusOfNotification type) {
        return repository.findNotificationStatusByStatus(type.getValue()).getId();
    }

    public String getNotificationStatus(Long id) {
        return repository.findNotificationStatusById(id).getStatus();
    }

    public void fill() {
        if (repository.count() == 0) {
            repository.save(new NotificationStatus(StatusOfNotification.NO_ANSWER.getValue()));
            repository.save(new NotificationStatus(StatusOfNotification.ANSWERED_SEEN.getValue()));
            repository.save(new NotificationStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue()));
        } else if (repository.count() > 3) {
            System.out.println("Что-то идет не так, почистите таблицу notification_status!!! В ней должно быть только 3 заранее добавленные записи!!!");
        }
    }
}
