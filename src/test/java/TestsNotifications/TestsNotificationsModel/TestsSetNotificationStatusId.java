package TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetNotificationStatusId {

    private static final Long NOTIFICATION_STATUS_ID_BEFORE = 10L;
    private static final Long NOTIFICATION_STATUS_ID_AFTER = 11L;

    /**
     * Проверяет работу setNotificationTypeId
     */
    @Test
    public void testSetNotificationTypeId() {
        Notifications notifications = new Notifications(1L, 1L, 4L, NOTIFICATION_STATUS_ID_BEFORE);
        assertEquals(NOTIFICATION_STATUS_ID_BEFORE, notifications.getNotificationStatusId(), "Изначально неверное значение");
        notifications.setNotificationStatusId(NOTIFICATION_STATUS_ID_AFTER);
        assertEquals(NOTIFICATION_STATUS_ID_AFTER, notifications.getNotificationStatusId(), "Установилось неверное значение");
    }

}
