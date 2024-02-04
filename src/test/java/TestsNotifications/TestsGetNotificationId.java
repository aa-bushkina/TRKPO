package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetNotificationId {


    /**
     * Проверяет, что изначельно getNotificationId выдает 0
     */
    @Test
    public void testGetNotificationIWithoutSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertEquals(0, notifications.getNotificationId(), "Возвращается неверное значение");
    }

}
