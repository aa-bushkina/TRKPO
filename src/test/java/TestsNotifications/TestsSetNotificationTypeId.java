package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetNotificationTypeId {

    private static final Long NOTIFICATION_TYPE_ID_BEFORE = 10L;
    private static final Long NOTIFICATION_TYPE_ID_AFTER = 11L;

    /**
     * Проверяет работу setNotificationTypeId
     */
    @Test
    public void testSetNotificationTypeId() {
        Notifications notifications = new Notifications(1L, 1L, NOTIFICATION_TYPE_ID_BEFORE, 1L);
        assertEquals(NOTIFICATION_TYPE_ID_BEFORE, notifications.getNotificationTypeId(), "Изначально неверное значение");
        notifications.setNotificationTypeId(NOTIFICATION_TYPE_ID_AFTER);
        assertEquals(NOTIFICATION_TYPE_ID_AFTER, notifications.getNotificationTypeId(), "Установилось неверное значение");
    }

}
