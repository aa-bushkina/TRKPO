package backTests.TestsLog.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест проверяет вызов метода getDate() в классе Log
 */
public class TestLogGetDate {
    /**
     * Проверяет, что метод возвращает ожидаемую дату
     */
    @Test
    public void testGetDate() {
        LocalDate expectedDate = LocalDate.of(2022, 2, 14);
        Log log = new Log();
        log.setDate(expectedDate);

        LocalDate actualDate = log.getDate();

        assertEquals(expectedDate, actualDate, "Метод getDate возвращает неверную дату");
    }

    /**
     * Проверяет, что метод возвращает null, если дата не установлена
     */
    @Test
    public void testGetDateWhenNotSet() {
        Log log = new Log();

        LocalDate actualDate = log.getDate();

        assertNull(actualDate, "Метод getDate не возвращает null, когда дата не установлена");
    }

    /**
     * Проверяет, что метод возвращает ожидаемую дату после изменения
     */
    @Test
    public void testGetDateAfterSet() {
        LocalDate initialDate = LocalDate.of(2022, 2, 14);
        LocalDate updatedDate = LocalDate.of(2023, 3, 15);
        Log log = new Log();
        log.setDate(initialDate);
        log.setDate(updatedDate);

        LocalDate actualDate = log.getDate();

        assertEquals(updatedDate, actualDate, "Метод getDate не возвращает верную дату после изменения");
    }
}
