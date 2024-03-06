package module.backTests.TestsNotifications.TestsNotificationsModel;

import com.cygans.database.notifications.Notifications;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetDate {

    private static final LocalDateTime DATE_BEFORE = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private static final LocalDateTime DATE_AFTER = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(1);

    /**
     * Проверяет работу setDate
     */
    @Test
    public void testSetQuestionId() {
        Notifications notifications = new Notifications(1L, 2L, 3L, 4L);
        assertEquals(DATE_BEFORE, notifications.getDate(), "Изначально неверное значение");
        notifications.setDate(DATE_AFTER);
        assertEquals(DATE_AFTER, notifications.getDate(), "Установилось неверное значение");
    }

}
