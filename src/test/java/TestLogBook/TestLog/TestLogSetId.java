package TestLogBook.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тест проверяет вызов метода setId() класса Log
 */
public class TestLogSetId {
    /**
     * Проверяет метод setId с положительным значением
     */
    @Test
    public void testSetIdWithPositiveValue() {
        long positiveId = 1L;
        Log log = new Log();

        log.setId(positiveId);

        assertEquals(positiveId, log.getId(), "setId не установил правильное положительное значение Id");
    }

    /**
     * Проверяет метод setId с нулевым значением
     */
    @Test
    public void testSetIdWithZeroValue() {
        long zeroId = 0L;
        Log log = new Log();

        log.setId(zeroId);

        assertEquals(zeroId, log.getId(), "setId не установил правильное значение Id равное нулю");
    }

    /**
     * Проверяет метод setId с отрицательным значением
     */
    @Test
    public void testSetIdWithNegativeValue() {
        long negativeId = -1L;
        Log log = new Log();

        assertThrows(IllegalArgumentException.class, () -> log.setId(negativeId),
                "setId не должен принимать отрицательные значения");
    }

    /**
     * Проверяет метод setId с максимально допустимым положительным значением long
     */
    @Test
    public void testSetIdWithMaxPositiveValue() {
        long maxPositiveId = Long.MAX_VALUE;
        Log log = new Log();

        log.setId(maxPositiveId);

        assertEquals(maxPositiveId, log.getId(), "setId не установил максимально допустимое положительное значение Id");
    }

    /**
     * Проверяет метод setId с использованием значения, которое не изменило бы текущее значение Id
     */
    @Test
    public void testSetIdWithSameValue() {
        long initialValue = 42L;
        Log log = new Log();
        log.setId(initialValue);
        log.setId(initialValue);

        assertEquals(initialValue, log.getId(), "setId изменил значение Id при передаче того же самого значения");
    }
}
