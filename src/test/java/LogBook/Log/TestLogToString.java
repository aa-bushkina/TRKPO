package LogBook.Log;

import com.cygans.database.log_book.Log;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тест проверяет вызов метода toString() класса Log
 */
public class TestLogToString {
    /**
     * Проверяет метод toString для объекта без установленных полей
     */
    @Test
    public void testToStringWithNoSetValues() {
        Log log = new Log();

        assertEquals("Log{id=0, date=null, logBookType='0', participantId=null}", log.toString(),
                "Метод toString не вернул ожидаемую строку для объекта без установленных полей");
    }

    /**
     * Проверяет метод toString для объекта со всеми установленными полями
     */
    @Test
    public void testToStringWithAllSetValues() {
        long id = 1L;
        LocalDate date = LocalDate.of(2022, 1, 1);
        long logTypeId = 2L;
        Long participantId = 3L;
        Log log = new Log(participantId, date, logTypeId);
        log.setId(id);

        assertEquals("Log{id=1, date=2022-01-01, logBookType='2', participantId=3}", log.toString(),
                "Метод toString не вернул ожидаемую строку для объекта со всеми установленными полями");
    }

    /**
     * Проверяет, что вызов метода toString после изменения полей возвращает обновленное значение
     */
    @Test
    public void testToStringAfterSetValues() {
        Log log = new Log();

        String initialToStringResult = log.toString();

        assertNotNull(initialToStringResult, "Метод toString не должен возвращать null");
        assertEquals("Log{id=0, date=null, logBookType='0', participantId=null}", log.toString(),
                "Метод toString не вернул ожидаемую строку для объекта без установленных полей");

        long id = 1L;
        LocalDate date = LocalDate.of(2022, 1, 1);
        Long participantId = 3L;
        log.setId(id);
        log.setParticipantId(participantId);
        log.setDate(date);

        String updatedToStringResult = log.toString();

        assertNotNull(updatedToStringResult, "Метод toString не должен возвращать null");
        assertEquals("Log{id=1, date=2022-01-01, logBookType='0', participantId=3}", updatedToStringResult,
                "Метод toString не вернул ожидаемую строку после изменения полей");
    }
}
