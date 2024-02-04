package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetNotificationStatusId {

    private static final Long NOTIFICATION_STATUS_ID = 1L;

    /**
     * Проверяет работу getNotificationStatusId
     */
    @Test
    public void testGetNotificationStatusId() {
        Notifications notifications = new Notifications(10L, 2L, 4L, NOTIFICATION_STATUS_ID);
        assertEquals(NOTIFICATION_STATUS_ID, notifications.getNotificationStatusId(), "Возвращается неверное значение");
    }

}
