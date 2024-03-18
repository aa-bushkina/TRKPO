package module_tests.backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
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
public class TestsGetNotificationStatusId {

    @Mock
    private NotificationStatusService notificationStatusService;

    @InjectMocks
    private NotificationController notificationController;

    /**
     * Тест для метода getNotificationStatusId().
     * Проверяет корректное получение идентификатора статуса уведомления.
     */
    @Test
    public void testGetNotificationStatusId() {
        StatusOfNotification fakeStatusOfNotification = StatusOfNotification.NO_ANSWER;
        when(notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)).thenReturn(1L);
        Long result = notificationController.getNotificationStatusId(fakeStatusOfNotification);
        assertEquals(1L, result);
        verify(notificationStatusService, times(1)).getNotificationStatusId(StatusOfNotification.NO_ANSWER);
    }

}
