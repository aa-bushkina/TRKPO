package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetAllMessage {

    private static final String ALL_MESSAGE = "Hello";

    /**
     * Проверяет работу getAllMessage без установки
     */
    @Test
    public void testGetAllMessageWithoutSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getAllMessage(), "Возвращается неверное значение");
    }

    /**
     * Проверяет работу getAllMessage после установки значения
     */
    @Test
    public void testGetAllMessageWithSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getAllMessage(), "Изначально неверное значение");
        notifications.setAllMessage(ALL_MESSAGE);
        assertEquals(ALL_MESSAGE, notifications.getAllMessage(), "Возвращается неверное значение");
    }

}
