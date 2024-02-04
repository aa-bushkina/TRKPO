package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetParticipantId {

    private static final Long PARTICIPANT_ID = 1L;

    /**
     * Проверяет работу getParticipantId
     */
    @Test
    public void testGetParticipantIdIWithoutSet() {
        Notifications notifications = new Notifications(PARTICIPANT_ID, 2L, 3L, 4L);
        assertEquals(PARTICIPANT_ID, notifications.getParticipantId(), "Возвращается неверное значение");
    }

}
