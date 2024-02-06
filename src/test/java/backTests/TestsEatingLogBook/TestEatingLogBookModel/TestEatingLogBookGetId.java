package backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для метода getId() класса EatingLogBook
 */
public class TestEatingLogBookGetId {
    /**
     * Проверяет, что метод возвращает корректное значение id
     */
    @Test
    public void testGetId() {
        long expectedId = 1L;
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setId(expectedId);

        assertEquals(expectedId, eatingLogBook.getId(), "Метод getId() возвращает неверное значение");
    }

    /**
     * Проверяет, что метод возвращает 0, если id установлен в 0
     */
    @Test
    public void testGetIdWithZeroValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setId(0L);

        assertEquals(0L, eatingLogBook.getId(), "Метод getId() возвращает неверное значение");
    }

    /**
     * Проверяет, что метод возвращает корректное значение id после изменения
     */
    @Test
    public void testGetIdAfterChange() {
        long initialId = 1L;
        long newId = 2L;
        EatingLogBook eatingLogBook = new EatingLogBook();

        eatingLogBook.setId(initialId);
        assertEquals(initialId, eatingLogBook.getId(), "Метод getId() возвращает неверное значение после первой установки");

        eatingLogBook.setId(newId);
        assertEquals(newId, eatingLogBook.getId(), "Метод getId() возвращает неверное значение после изменения");
    }
}
