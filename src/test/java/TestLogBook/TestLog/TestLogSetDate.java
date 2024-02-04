package TestLogBook.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест проверяет вызов метода setDate() в классе Log
 */
public class TestLogSetDate {
    /**
     * Проверяет, что метод устанавливает ожидаемую дату
     */
    @Test
    public void testSetDate() {
        LocalDate expectedDate = LocalDate.of(2022, 2, 14);
        Log log = new Log();

        log.setDate(expectedDate);

        assertEquals(expectedDate, log.getDate(), "Метод setDate не устанавливает верную дату");
    }

    /**
     * Проверяет, что метод правильно обновляет дату после изменения
     */
    @Test
    public void testUpdateDate() {
        LocalDate initialDate = LocalDate.of(2022, 2, 14);
        LocalDate updatedDate = LocalDate.of(2023, 3, 15);
        Log log = new Log();
        log.setDate(initialDate);

        log.setDate(updatedDate);

        assertEquals(updatedDate, log.getDate(), "Метод setDate не обновляет верную дату");
    }

    /**
     * Проверяет, что метод устанавливает null, если передан null
     */
    @Test
    public void testSetDateWithNull() {
        LocalDate initialDate = LocalDate.of(2022, 2, 14);
        Log log = new Log();
        log.setDate(initialDate);

        log.setDate(null);

        assertNull(log.getDate(), "Метод setDate не устанавливает null, когда передан null");
    }
}
