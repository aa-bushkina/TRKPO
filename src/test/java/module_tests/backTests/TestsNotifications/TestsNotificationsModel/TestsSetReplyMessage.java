package module_tests.backTests.TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsSetReplyMessage {

    private static final String REPLY_MESSAGE = "Hello";

    /**
     * Проверяет работу setAllMessage
     */
    @Test
    public void testsetReplyMessage() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getReplyMessage(), "Изначально неверное значение");
        notifications.setReplyMessage(REPLY_MESSAGE);
        assertEquals(REPLY_MESSAGE, notifications.getReplyMessage(), "Установилось неверное значение");
    }

}
