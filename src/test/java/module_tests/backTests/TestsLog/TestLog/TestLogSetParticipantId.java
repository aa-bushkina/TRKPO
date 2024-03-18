package module_tests.backTests.TestsLog.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест проверяет вызов метода setParticipantId() класса Log
 */
public class TestLogSetParticipantId {
    /**
     * Проверяет, что метод устанавливает ожидаемое значение participantId
     */
    @Test
    public void testSetParticipantId() {
        Long expectedParticipantId = 1L;
        Log log = new Log();

        log.setParticipantId(expectedParticipantId);

        assertEquals(expectedParticipantId, log.getParticipantId(), "Метод setParticipantId() не устанавливает ожидаемое значение");
    }

    /**
     * Проверяет, что метод корректно устанавливает null в participantId
     */
    @Test
    public void testSetParticipantIdWithNull() {
        Log log = new Log();

        log.setParticipantId(null);

        assertNull(log.getParticipantId(), "Метод setParticipantId() не устанавливает null в participantId");
    }

    /**
     * Проверяет, что метод корректно устанавливает значение participantId, равное null
     */
    @Test
    public void testSetParticipantIdWithNullValue() {
        Long expectedParticipantId = null;
        Log log = new Log();

        log.setParticipantId(expectedParticipantId);

        assertEquals(expectedParticipantId, log.getParticipantId(), "Метод setParticipantId() не устанавливает значение, равное null");
    }
}
