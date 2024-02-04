package TestLogBook.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест провеяет вызов getId класса Log
 */
public class TestLogGetId {
    /**
     * Проверяет метод getId для объекта с установленным положительным id
     */
    @Test
    public void testGetIdWithPositiveValue() {
        long positiveId = 1L;
        Log log = new Log();
        log.setId(positiveId);

        assertEquals(positiveId, log.getId(), "Метод getId не вернул правильное положительное значение Id");
    }

    /**
     * Проверяет метод getId для объекта с установленным нулевым id
     */
    @Test
    public void testGetIdWithZeroValue() {
        long zeroId = 0L;
        Log log = new Log();
        log.setId(zeroId);

        assertEquals(zeroId, log.getId(), "Метод getId не вернул правильное значение Id равное нулю");
    }

    /**
     * Проверяет метод getId для объекта без установленного id
     */
    @Test
    public void testGetIdWithoutSetValue() {
        Log log = new Log();

        assertEquals(0L, log.getId(), "Метод getId не вернул значение Id по умолчанию (ноль)");
    }

    /**
     * Проверяет метод getId для объекта с установленным отрицательным id
     */
    @Test
    public void testGetIdWithNegativeValue() {
        long negativeId = -1L;
        Log log = new Log();
        // setId с отрицательным значением должен выбросить исключение, но проверим на всякий случай
        try {
            log.setId(negativeId);
        } catch (IllegalArgumentException ignored) {
        }

        assertEquals(0L, log.getId(), "Метод getId не вернул значение Id по умолчанию после установки отрицательного значения");
    }
}
