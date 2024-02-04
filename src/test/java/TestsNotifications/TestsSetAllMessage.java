package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsSetAllMessage {

    private static final String ALL_MESSAGE = "Hello";

    /**
     * Проверяет работу setAllMessage
     */
    @Test
    public void testSetAllMessage() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getAllMessage(), "Изначально неверное значение");
        notifications.setAllMessage(ALL_MESSAGE);
        assertEquals(ALL_MESSAGE, notifications.getAllMessage(), "Установилось неверное значение");
    }

}
