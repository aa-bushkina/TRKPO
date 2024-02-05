package TestsNotifications.TestsNotificationsService;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsRepository;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatus;
import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsResolveRequest {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод resolveRequest устанавливает статус "ANSWERED_NOT_SEEN",
     * если уведомление существует и его текущий статус "NO_ANSWER".
     */
    @Test
    public void testResolveRequestWhenNotificationExistsAndCurrentStatusIsNoAnswer() {
        long notificationId = 1L;
        Notifications notification = new Notifications();
        notification.setNotificationStatusId(new NotificationStatus(StatusOfNotification.NO_ANSWER.getValue()).getId());
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(notification);
        NotificationStatus answeredNotSeenStatus = new NotificationStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue());
        when(notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue()))
                .thenReturn(answeredNotSeenStatus);
        service.resolveRequest(notificationId);
        assertEquals(answeredNotSeenStatus.getId(), notification.getNotificationStatusId());
        verify(notificationsRepository, times(1)).save(notification);
    }

}
