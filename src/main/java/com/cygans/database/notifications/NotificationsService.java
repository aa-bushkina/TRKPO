package com.cygans.database.notifications;

import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {
    @Autowired
    private NotificationsRepository notificationsRepository;
    @Autowired
    private NotificationStatusRepository notificationStatusRepository;
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    public void resolveRequest(long id) {
        Notifications notification = notificationsRepository.getNotificationById(id);
        notification.setNotificationStatusId(notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue()).getId());
        notificationsRepository.save(notification);
    }

    public void reply(long id, String msg) {
        Notifications notification = notificationsRepository.getNotificationById(id);
        notification.setReplyMessage(msg);
        notificationsRepository.save(notification);
    }


    public List<Notifications> getMentorNotificationlist(Long mentorId) {
        Long status = notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.NO_ANSWER.getValue()).getId();
        return notificationsRepository.getNotificationByMentorId(mentorId)
                .stream()
                .filter(notifications -> notifications.getNotificationStatusId().equals(status))
                .sorted()
                .toList();
    }

    public List<Notifications> getNotificationsWithAnswerNotSeenList(Long participantId) {
        return notificationsRepository
                .getNotificationsByParticipantId(participantId)
                .stream()
                .filter(notification -> notification.getNotificationStatusId()
                        .equals(notificationStatusRepository.findNotificationStatusByStatus(
                                StatusOfNotification.ANSWERED_NOT_SEEN.getValue())
                                .getId()))
                .filter(notifications -> !notifications.getNotificationTypeId()
                        .equals(notificationTypeRepository.findNotificationTypeByType(
                                TypeOfNotification.DECLINE_MENTOR.getValue())
                                .getId()))
                .toList();
    }

    public Notifications getNotificationById(long id) {
        return notificationsRepository.getNotificationById(id);
    }

    public Notifications getNotificationByLogBookId(long id) {
        return notificationsRepository.getNotificationByLogBookId(id);
    }

    public void updateNotificationStatus(Long id, Long statusId) {
        Notifications notification = notificationsRepository.getNotificationById(id);
        notification.setNotificationStatusId(statusId);
        notificationsRepository.save(notification);
    }

    public void updateNotificationType(Long id, Long typeId) {
        Notifications notification = notificationsRepository.getNotificationById(id);
        notification.setNotificationTypeId(typeId);
        notificationsRepository.save(notification);
    }

    public void updateNotificationShortMessage(Long id, String shortMsg) {
        Notifications notification = notificationsRepository.getNotificationById(id);
        notification.setShortMessage(shortMsg);
        notificationsRepository.save(notification);
    }

    public void updateNotificationAllMessage(Long id, String allMsg) {
        Notifications notification = notificationsRepository.getNotificationById(id);
        notification.setAllMessage(allMsg);
        notificationsRepository.save(notification);
    }

    public void saveNotification(Notifications notification) {
        notificationsRepository.save(notification);
    }

}
