package TestsNotifications.TestsNotificationType;

import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsGetId {

    /**
     * Проверяет работу getId
     */
    @Test
    public void testGetId() {
        NotificationType notificationType = new NotificationType(TypeOfNotification.ANSWER_ON_QUESTION.getValue());
        assertNull(notificationType.getId(), "ID не null");
    }

}
