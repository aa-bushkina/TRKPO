package module_tests.backTests.TestsLog.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест проверяет вызов метода getParticipantId() класса Log
 */
public class TestLogGetParticipantId {
    /**
     * Проверяет, что метод возвращает ожидаемое значение participantId
     */
    @Test
    public void testGetParticipantId() {
        Long expectedParticipantId = 1L;
        Log log = new Log(expectedParticipantId, null, 0L);

        Long result = log.getParticipantId();

        assertEquals(expectedParticipantId, result, "Метод getParticipantId() не возвращает ожидаемое значение");
    }

    /**
     * Проверяет, что метод возвращает null для лога без participantId
     */
    @Test
    public void testGetParticipantIdWithNull() {
        Log log = new Log(null, null, 0L);

        Long result = log.getParticipantId();

        assertNull(result, "Метод getParticipantId() не возвращает null для лога без participantId");
    }

    /**
     * Проверяет, что метод возвращает ожидаемое значение participantId для лога с null в participantId
     */
    @Test
    public void testGetParticipantIdWithNullValue() {
        Long expectedParticipantId = null;
        Log log = new Log(expectedParticipantId, null, 0L);

        Long result = log.getParticipantId();

        assertEquals(expectedParticipantId, result, "Метод getParticipantId() не возвращает ожидаемое значение для null в participantId");
    }
}
