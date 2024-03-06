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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationById {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод getNotificationById возвращает
     * уведомление с указанным идентификатором, если оно существует.
     */
    @Test
    public void testGetNotificationById() {
        Long notificationId = 1L;
        Notifications mockNotification = new Notifications(notificationId, 1L, 2L, 2L);
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(mockNotification);
        Notifications result = service.getNotificationById(notificationId);
        assertEquals(mockNotification, result, "Неверный объект вернулся");
    }

    /**
     * Тест проверяет, что метод getNotificationById возвращает
     * null, если уведомление с указанным идентификатором не существует.
     */
    @Test
    public void testGetNotificationByIdWhenNotificationDoesNotExist() {
        Long notificationId = 1L;
        Notifications result = service.getNotificationById(notificationId);
        assertNull(result, "Объект не null");
    }

}
