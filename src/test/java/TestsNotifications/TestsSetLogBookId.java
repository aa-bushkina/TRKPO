package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsSetLogBookId {

    private static final Long LOG_BOOK_ID = 2L;

    /**
     * Проверяет работу setLogBookId
     */
    @Test
    public void testSetLogBookId() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getLogBookId(), "Изначально неверное значение");
        notifications.setLogBookId(LOG_BOOK_ID);
        assertEquals(LOG_BOOK_ID, notifications.getLogBookId(), "Установилось неверное значение");
    }

}
