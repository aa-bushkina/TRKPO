package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetQuestionId {

    /**
     * Проверяет работу getQuestionId
     */
    @Test
    public void testGetMentorIdIWithoutSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getQuestionId(), "Возвращается неверное значение");
    }

}
