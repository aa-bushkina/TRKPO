package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetLogBookId {

    private static final Long LOG_BOOK_ID = 2L;

    /**
     * Проверяет работу getLogBookId без установки
     */
    @Test
    public void testGetLogBookIdWithoutSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getLogBookId(), "Возвращается неверное значение");
    }

    /**
     * Проверяет работу getLogBookId после установки значения
     */
    @Test
    public void testGetLogBookIdWithSet() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertNull(notifications.getLogBookId(), "Изначально неверное значение");
        notifications.setLogBookId(LOG_BOOK_ID);
        assertEquals(LOG_BOOK_ID, notifications.getLogBookId(), "Возвращается неверное значение");
    }

}
