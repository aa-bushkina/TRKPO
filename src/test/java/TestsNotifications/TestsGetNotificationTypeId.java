package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetNotificationTypeId {

    private static final Long NOTIFICATION_TYPE_ID = 1L;

    /**
     * Проверяет работу getNotificationTypeId
     */
    @Test
    public void testGetNotificationTypeId() {
        Notifications notifications = new Notifications(10L, 2L, NOTIFICATION_TYPE_ID, 4L);
        assertEquals(NOTIFICATION_TYPE_ID, notifications.getNotificationTypeId(), "Возвращается неверное значение");
    }

}
