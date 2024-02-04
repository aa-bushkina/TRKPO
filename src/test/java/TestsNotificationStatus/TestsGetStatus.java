package TestsNotificationStatus;

import com.cygans.database.notifications.notification_status.NotificationStatus;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetStatus {

    /**
     * Проверяет вызов метода для объекта с status = null
     */
    @Test
    public void testGetStatusNull() {
        NotificationStatus notificationStatus = new NotificationStatus();
        assertNull(notificationStatus.getStatus(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с существующим status
     */
    @Test
    public void testGetStatusNotNull() {
        String validType = StatusOfNotification.ANSWERED_NOT_SEEN.getValue();
        NotificationStatus notificationStatus = new NotificationStatus(validType);
        assertEquals(validType, notificationStatus.getStatus(), "Тип не соответствует ожидаемому значению");
    }

}
