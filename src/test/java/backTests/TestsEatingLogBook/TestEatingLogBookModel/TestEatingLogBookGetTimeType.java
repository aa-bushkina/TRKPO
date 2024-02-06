package backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для метода getTimeType() класса EatingLogBook
 */
public class TestEatingLogBookGetTimeType {
    /**
     * Проверяет, что метод возвращает корректное значение timeType, когда оно установлено
     */
    @Test
    public void testGetTimeTypeWhenSet() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        LocalDateTime expectedTimeType = LocalDateTime.now();
        eatingLogBook.setTimeType(expectedTimeType);

        LocalDateTime actualTimeType = eatingLogBook.getTimeType();

        assertEquals(expectedTimeType, actualTimeType, "Метод getTimeType() вернул неверное значение");
    }

    /**
     * Проверяет, что метод возвращает null, когда timeType не установлено
     */
    @Test
    public void testGetTimeTypeWhenNotSet() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        LocalDateTime actualTimeType = eatingLogBook.getTimeType();

        assertNull(actualTimeType, "Метод getTimeType() должен возвращать null, когда значение не установлено");
    }

    /**
     * Проверяет, что метод возвращает корректное значение timeType после изменения
     */
    @Test
    public void testGetTimeTypeAfterChange() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        LocalDateTime initialTimeType = LocalDateTime.now();
        eatingLogBook.setTimeType(initialTimeType);

        LocalDateTime newTimeType = LocalDateTime.now().plusDays(1);
        eatingLogBook.setTimeType(newTimeType);

        LocalDateTime actualTimeType = eatingLogBook.getTimeType();

        assertEquals(newTimeType, actualTimeType, "Метод getTimeType() вернул неверное значение после изменения");
    }
}
