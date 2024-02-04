package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для метода getLogBookId() класса EatingLogBook
 */
public class TestEatingLogBookGetLogBookId {
    /**
     * Проверяет, что метод возвращает корректное значение logBookId
     */
    @Test
    public void testGetLogBookIdWithValidValue() {
        long expectedLogBookId = 1L;
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setLogBookId(expectedLogBookId);

        long actualLogBookId = eatingLogBook.getLogBookId();

        assertEquals(expectedLogBookId, actualLogBookId, "Метод getLogBookId() вернул неверное значение");
    }

    /**
     * Проверяет, что метод возвращает корректное значение logBookId после изменения
     */
    @Test
    public void testGetLogBookIdAfterChange() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long initialLogBookId = 1L;
        eatingLogBook.setLogBookId(initialLogBookId);

        long newLogBookId = 2L;
        eatingLogBook.setLogBookId(newLogBookId);

        long actualLogBookId = eatingLogBook.getLogBookId();

        assertEquals(newLogBookId, actualLogBookId, "Метод getLogBookId() вернул неверное значение после изменения");
    }

    /**
     * Проверяет, что метод возвращает 0, если logBookId не установлен
     */
    @Test
    public void testGetLogBookIdWithoutSetValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        long actualLogBookId = eatingLogBook.getLogBookId();

        assertEquals(0, actualLogBookId, "Метод getLogBookId() должен возвращать 0, если logBookId не установлен");
    }
}
