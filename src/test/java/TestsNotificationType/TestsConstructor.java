package TestsNotificationType;

import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsConstructor {

    /**
     * Проверяет работу конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        NotificationType notificationType = new NotificationType(TypeOfNotification.ANSWER_ON_QUESTION.getValue());
        assertAll(
                () -> assertNull(notificationType.getId(), "ID не null"),
                () -> assertEquals(TypeOfNotification.ANSWER_ON_QUESTION.getValue(), notificationType.getType(), "Type не совпадает")
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        NotificationType notificationType = new NotificationType();
        assertAll(
                () -> assertNull(notificationType.getId(), "ID не null"),
                () -> assertNull(notificationType.getType(), "Type не null")
        );
    }

    /**
     * Проверяет вызов конструктора с типом null
     */
    @Test
    public void testConstructorWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> new NotificationType(null),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

    /**
     * Проверяет вызов конструктора с пустым типом
     */
    @Test
    public void testConstructorWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> new NotificationType(""),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

}
