package TestsNotifications.TestsNotificationsService;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsUpdateNotificationStatus {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод updateNotificationStatus правильно
     * обновляет статус уведомления.
     */
    @Test
    public void testUpdateNotificationStatus() {
        Long notificationId = 1L;
        Long newStatusId = 2L;
        Notifications mockNotification = new Notifications(notificationId, 3L, 4L, 1L);
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(mockNotification);
        service.updateNotificationStatus(notificationId, newStatusId);
        assertEquals(newStatusId, mockNotification.getNotificationStatusId(), "Неверный statusId");
        verify(notificationsRepository, times(1)).save(mockNotification);
    }

}
