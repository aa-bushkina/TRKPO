package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetReplyMessage {

    private static final String REPLY_MESSAGE = "Hello";

    /**
     * Проверяет работу getReplyMessage без установки
     */
    @Test
    public void testGetReplyMessageeWithoutSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getReplyMessage(), "Возвращается неверное значение");
    }

    /**
     * Проверяет работу getReplyMessage после установки значения
     */
    @Test
    public void testGetReplyMessageWithSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getReplyMessage(), "Изначально неверное значение");
        notifications.setReplyMessage(REPLY_MESSAGE);
        assertEquals(REPLY_MESSAGE, notifications.getReplyMessage(), "Возвращается неверное значение");
    }

}
