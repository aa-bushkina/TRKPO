package TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsCompareTo {


    /**
     * Сравнение, когда у вызывающий объект меньше
     */
    @Test
    public void testCompareToFirstLess() throws Exception {
        Notifications notifications1 = new Notifications(1L, 2L, 3L, 4L);
        Thread.sleep(2000L);
        Notifications notifications2 = new Notifications(1L, 2L, 3L, 4L);
        assertTrue(notifications2.compareTo(notifications1) < 0, "compareTo вернул неверный результат");
    }

    /**
     * Сравнение, когда у вызывающий объект больше
     */
    @Test
    public void testCompareToFirstMore() throws Exception {
        Notifications notifications1 = new Notifications(1L, 2L, 3L, 4L);
        Thread.sleep(2000L);
        Notifications notifications2 = new Notifications(1L, 2L, 3L, 4L);
        assertTrue(notifications1.compareTo(notifications2) > 0, "compareTo вернул неверный результат");
    }

    /**
     * Сравнение, когда объекты равны
     */
    @Test
    public void testCompareToEquals() {
        Notifications notifications1 = new Notifications(1L, 2L, 3L, 4L);
        Notifications notifications2 = new Notifications(1L, 2L, 3L, 4L);
        assertEquals(0, notifications1.compareTo(notifications2), "compareTo вернул неверный результат");
    }

}
