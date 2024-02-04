package TestsNotifications.TestsNotificationStatus;

import com.cygans.database.notifications.notification_status.NotificationStatus;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetId {

    /**
     * Проверяет работу getId
     */
    @Test
    public void testGetId() {
        NotificationStatus notificationStatus = new NotificationStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue());
        assertNull(notificationStatus.getId(), "ID не null");
    }

}
