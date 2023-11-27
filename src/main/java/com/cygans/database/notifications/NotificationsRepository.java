package com.cygans.database.notifications;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationsRepository extends CrudRepository<Notifications, Long> {

    List<Notifications> getNotificationsByParticipantId(Long id);

    List<Notifications> getNotificationByMentorId(Long id);

    Notifications getNotificationById(long id);

    Notifications getNotificationByLogBookId(long id);

    @Override
    <S extends Notifications> S save(S s);

}