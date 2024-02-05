package TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsSetQuestionId {

    private static final Long QUESTION_ID = 2L;

    /**
     * Проверяет работу setQuestionId
     */
    @Test
    public void testSetQuestionId() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getQuestionId(), "Изначально неверное значение");
        notifications.setQuestionId(QUESTION_ID);
        assertEquals(QUESTION_ID, notifications.getQuestionId(), "Установилось неверное значение");
    }

}
