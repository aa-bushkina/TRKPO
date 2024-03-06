package module.backTests.TestsNotifications.TestsNotificationStatusService;

import com.cygans.database.notifications.notification_status.NotificationStatus;
import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationStatusId {

    @Mock
    private NotificationStatusRepository repository;

    @InjectMocks
    private NotificationStatusService service;

    /**
     * Тест проверяет, что метод getNotificationStatusId возвращает правильный ID
     * для заданного значения StatusOfNotification из репозитория.
     */
    @Test
    public void testGetNotificationStatusId() {
        StatusOfNotification status = StatusOfNotification.ANSWERED_SEEN;
        NotificationStatus expectedStatus = new NotificationStatus(status.getValue());
        when(repository.findNotificationStatusByStatus(status.getValue())).thenReturn(expectedStatus);
        Long result = service.getNotificationStatusId(status);
        assertEquals(expectedStatus.getId(), result, "Вернулся неверный id");
    }

}
