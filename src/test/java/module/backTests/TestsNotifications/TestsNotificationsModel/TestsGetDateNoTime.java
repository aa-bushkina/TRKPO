package module.backTests.TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetDateNoTime {

    private static final LocalDate DATE = LocalDate.now();

    /**
     * Проверяет работу getDateNoTime
     */
    @Test
    public void testGetDateNoTime() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertEquals(DATE, notifications.getDateNoTime(), "Возвращается неверное значение");
    }

}
