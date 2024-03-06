package module.backTests.TestsNotifications.TestsNotificationsService;

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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetMentorNotificationlist {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод getMentorNotificationlist возвращает
     * список уведомлений с указанным статусом для ментора.
     */
    @Test
    public void testGetMentorNotificationlist() {
        Long mentorId = 1L;
        Long statusId = 1L;
        NotificationStatus notificationStatus = new NotificationStatus();
        notificationStatus.setId(statusId);
        List<Notifications> mockNotifications = Arrays.asList(
                new Notifications(1L, mentorId, 1L, statusId),
                new Notifications(2L, mentorId, 1L, statusId),
                new Notifications(3L, mentorId, 1L, statusId)
        );
        when(notificationsRepository.getNotificationByMentorId(mentorId)).thenReturn(mockNotifications);
        when(notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.NO_ANSWER.getValue())).thenReturn(notificationStatus);
        List<Notifications> result = service.getMentorNotificationlist(mentorId);
        assertEquals(mockNotifications.size(), result.size());
        assertEquals(statusId, result.get(0).getNotificationStatusId());
        assertEquals(statusId, result.get(1).getNotificationStatusId());
        assertEquals(statusId, result.get(2).getNotificationStatusId());
    }

    /**
     * Тест проверяет, что метод getMentorNotificationlist возвращает
     * пустой список, если для ментора нет уведомлений с указанным статусом.
     */
    @Test
    public void testGetMentorNotificationlistWhenNoNotifications() {
        Long mentorId = 1L;
        Long statusId = 1L;
        NotificationStatus notificationStatus = new NotificationStatus();
        notificationStatus.setId(statusId);
        when(notificationsRepository.getNotificationByMentorId(mentorId)).thenReturn(List.of());
        when(notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.NO_ANSWER.getValue())).thenReturn(notificationStatus);
        List<Notifications> result = service.getMentorNotificationlist(mentorId);
        assertEquals(0, result.size());
    }

}
