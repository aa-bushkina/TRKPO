package TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет вызов метода setShortMessage() класса Notifications
 */
public class TestsSetShortMessage {
    /**
     * Проверяет, что метод setShortMessage корректно устанавливает короткое сообщение.
     */
    @Test
    public void testSetShortMessage() {
        Notifications notifications = new Notifications();
        String shortMessage = "Test Short Message";
        notifications.setShortMessage(shortMessage);

        assertEquals(shortMessage, notifications.getShortMessage(), "Short message should be set correctly");
    }

    /**
     * Проверяет, что метод setShortMessage корректно обрабатывает значение null
     */
    @Test
    public void testSetShortMessageNull() {
        Notifications notifications = new Notifications();
        notifications.setShortMessage(null);
        assertEquals(null, notifications.getShortMessage(), "Short message should be set to null");
    }

    /**
     * Проверяет, что метод setShortMessage корректно обрабатывает пустую строку
     */
    @Test
    public void testSetShortMessageEmpty() {
        Notifications notifications = new Notifications();
        notifications.setShortMessage("");
        assertEquals("", notifications.getShortMessage(), "Short message should be set to an empty string");
    }
}
