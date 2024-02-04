package TestsNotificationStatus;

import com.cygans.database.notifications.notification_status.NotificationStatus;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsConstructor {

    /**
     * Проверяет работу конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        NotificationStatus notificationStatus = new NotificationStatus(StatusOfNotification.NO_ANSWER.getValue());
        assertAll(
                () -> assertNull(notificationStatus.getId(), "ID не null"),
                () -> assertEquals(StatusOfNotification.NO_ANSWER.getValue(), notificationStatus.getStatus(), "Status не совпадает")
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        NotificationStatus notificationStatus = new NotificationStatus();
        assertAll(
                () -> assertNull(notificationStatus.getId(), "ID не null"),
                () -> assertNull(notificationStatus.getStatus(), "Status не null")
        );
    }

    /**
     * Проверяет вызов конструктора с типом null
     */
    @Test
    public void testConstructorWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> new NotificationStatus(null),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

    /**
     * Проверяет вызов конструктора с пустым типом
     */
    @Test
    public void testConstructorWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> new NotificationStatus(""),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

}
