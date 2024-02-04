package TestsNotifications.TestsNotificationType;

import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetType {

    /**
     * Проверяет вызов метода для объекта с type = null
     */
    @Test
    public void testGetTypeNull() {
        NotificationType logsType = new NotificationType();
        assertNull(logsType.getType(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с существующим type
     */
    @Test
    public void testGetTypeNotNull() {
        String validType = TypeOfNotification.ANSWER_ON_QUESTION.getValue();
        NotificationType logsType = new NotificationType(validType);
        assertEquals(validType, logsType.getType(), "Тип не соответствует ожидаемому значению");
    }

}
