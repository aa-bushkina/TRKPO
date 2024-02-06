package backTests.TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetMentorId {

    private static final Long MENTOR_ID = 1L;

    /**
     * Проверяет работу getMentorId
     */
    @Test
    public void testGetMentorId() {
        Notifications notifications = new Notifications(1L, MENTOR_ID, 3L, 4L);
        assertEquals(MENTOR_ID, notifications.getMentorId(), "Возвращается неверное значение");
    }

}
