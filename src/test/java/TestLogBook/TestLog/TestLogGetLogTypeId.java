package TestLogBook.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет вызов метода getLogTypeId() класса Log
 */
public class TestLogGetLogTypeId {
    /**
     * Проверяет, что метод возвращает ожидаемый тип лога
     */
    @Test
    public void testGetLogTypeId() {
        long expectedLogTypeId = 42L;
        Log log = new Log(null, null, expectedLogTypeId);

        long actualLogTypeId = log.getLogTypeId();

        assertEquals(expectedLogTypeId, actualLogTypeId, "Метод getLogTypeId() не возвращает верный идентификатор типа лога");
    }

    /**
     * Проверяет, что метод возвращает 0 по умолчанию, если тип не установлен
     */
    @Test
    public void testGetLogTypeIdDefault() {
        Log log = new Log();

        long actualLogTypeId = log.getLogTypeId();

        assertEquals(0L, actualLogTypeId, "Метод getLogTypeId() не возвращает 0 по умолчанию");
    }
}
