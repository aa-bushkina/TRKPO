package com.cygans.database.notifications;

import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationStatusRepository notificationStatusRepository;

    public void resolveRequest(long id) {
        Notifications notification = notificationRepository.getNotificationById(id);
        notification.setNotificationStatusId(notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue()).getId());
        notificationRepository.save(notification);
    }

    public void reply(long id, String msg) {
        Notifications notification = notificationRepository.getNotificationById(id);
        notification.setReplyMessage(msg);
        notificationRepository.save(notification);
    }


    public List<Notifications> getMentorNotificationlist(Long mentorId) {
        Long status = notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.NO_ANSWER.getValue()).getId();
        return notificationRepository.getNotificationByMentorId(mentorId)
                .stream()
                .filter(notifications -> notifications.getNotificationStatusId().equals(status))
                .sorted()
                .toList();
    }

    public List<Notifications> getNotificationWithAnswerNotSeenList(Long participantId) {
        return notificationRepository
                .getNotificationsByParticipantId(participantId)
                .stream()
                .filter(notification -> notification.getNotificationStatusId()
                        .equals(notificationStatusRepository.findNotificationStatusByStatus(
                                StatusOfNotification.ANSWERED_NOT_SEEN.getValue())
                                .getId()))
                .toList();
    }

    public Notifications getNotificationById(long id) {
        return notificationRepository.getNotificationById(id);
    }

    public Notifications getNotificationByLogBookId(long id) {
        return notificationRepository.getNotificationByLogBookId(id);
    }

    public void updateNotificationStatus(Long id, Long statusId) {
        Notifications notification = notificationRepository.getNotificationById(id);
        notification.setNotificationStatusId(statusId);
        notificationRepository.save(notification);
    }

    public void updateNotificationType(Long id, Long typeId) {
        Notifications notification = notificationRepository.getNotificationById(id);
        notification.setNotificationTypeId(typeId);
        notificationRepository.save(notification);
    }


    public void saveNotification(Notifications notification) {
        notificationRepository.save(notification);
    }

}
