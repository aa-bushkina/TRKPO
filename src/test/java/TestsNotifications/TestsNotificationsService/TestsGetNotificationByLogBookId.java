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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationByLogBookId {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод getNotificationByLogBookId возвращает
     * уведомление с указанным идентификатором записи журнала, если оно существует.
     */
    @Test
    public void testGetNotificationByLogBookId() {
        Long logBookId = 1L;
        Notifications mockNotification = new Notifications(1L, 2L, 3L, logBookId);
        when(notificationsRepository.getNotificationByLogBookId(logBookId)).thenReturn(mockNotification);
        Notifications result = service.getNotificationByLogBookId(logBookId);
        assertEquals(mockNotification, result, "Неверный вернулся объект");
    }

    /**
     * Тест проверяет, что метод getNotificationByLogBookId возвращает
     * null, если уведомление с указанным идентификатором записи журнала не существует.
     */
    @Test
    public void testGetNotificationByLogBookIdWhenNotificationDoesNotExist() {
        Long logBookId = 1L;
        when(notificationsRepository.getNotificationByLogBookId(logBookId)).thenReturn(null);
        Notifications result = service.getNotificationByLogBookId(logBookId);
        assertNull(result, "Объект не пустой");
    }

}
