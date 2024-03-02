package backTests.TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест проверяет вызов метода getShortMessage() класса Notifications
 */
public class TestsGetShortMessage {
    /**
     * Проверяет, что метод возвращает ожидаемое краткое сообщение
     */
    @Test
    public void testGetShortMessage() {
        Notifications notifications = new Notifications();
        String expectedShortMessage = "Это краткое сообщение";
        notifications.setShortMessage(expectedShortMessage);
        assertEquals(expectedShortMessage, notifications.getShortMessage());
    }

    /**
     * Проверяет, что метод возвращает null, если краткое сообщение не установлено
     */
    @Test
    public void testGetShortMessageWhenNotSet() {
        Notifications notifications = new Notifications();
        assertNull(notifications.getShortMessage());
    }

    /**
     * Проверяет, что метод возвращает ожидаемое краткое сообщение после изменения значения
     */
    @Test
    public void testGetShortMessageAfterUpdate() {
        Notifications notifications = new Notifications();
        String initialShortMessage = "Исходное краткое сообщение";
        notifications.setShortMessage(initialShortMessage);

        String updatedShortMessage = "Обновленное краткое сообщение";
        notifications.setShortMessage(updatedShortMessage);

        assertEquals(updatedShortMessage, notifications.getShortMessage());
    }
}
