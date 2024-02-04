package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetDate {

    private static final LocalDateTime DATE = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    /**
     * Проверяет работу getDate
     */
    @Test
    public void testGetDate() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertEquals(DATE, notifications.getDate(), "Возвращается неверное значение");
    }

}
