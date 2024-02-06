package backTests.TestsNotifications.TestsNotificationsService;

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
public class TestsUpdateNotificationType {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод updateNotificationType правильно
     * обновляет тип уведомления.
     */
    @Test
    public void testUpdateNotificationType() {
        Long notificationId = 1L;
        Long newTypeId = 2L;
        Notifications mockNotification = new Notifications(notificationId, 2L, 4L, 1L);
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(mockNotification);
        service.updateNotificationType(notificationId, newTypeId);
        assertEquals(newTypeId, mockNotification.getNotificationTypeId(), "неверный typeId");
        verify(notificationsRepository, times(1)).save(mockNotification);
    }

}
