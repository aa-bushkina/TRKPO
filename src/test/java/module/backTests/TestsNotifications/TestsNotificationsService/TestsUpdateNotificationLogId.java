package module.backTests.TestsNotifications.TestsNotificationsService;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsRepository;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
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
public class TestsUpdateNotificationLogId {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод updateNotificationLogId правильно
     * обновляет идентификатор записи журнала в уведомлении.
     */
    @Test
    public void testUpdateNotificationLogId() {
        Long notificationId = 1L;
        Long newLogBookId = 2L;
        Notifications mockNotification = new Notifications(notificationId, 2L, 4L, 1L);
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(mockNotification);
        service.updateNotificationLogId(notificationId, newLogBookId);
        assertEquals(newLogBookId, mockNotification.getLogBookId(), "неверный LogBookId");
        verify(notificationsRepository, times(1)).save(mockNotification);
    }

}
