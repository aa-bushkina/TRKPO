package backTests.TestsEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для метода setLogBookId(long logBookId) класса EatingLogBook
 */
public class TestEatingLogBookSetLogBookId {
    /**
     * Проверяет, что метод устанавливает корректное значение logBookId
     */
    @Test
    public void testSetLogBookIdWithValidValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long expectedLogBookId = 1L;

        eatingLogBook.setLogBookId(expectedLogBookId);

        long actualLogBookId = eatingLogBook.getLogBookId();
        assertEquals(expectedLogBookId, actualLogBookId, "Метод setLogBookId() не установил верное значение");
    }

    /**
     * Проверяет, что метод кидает исключение, если logBookId меньше 0
     */
    @Test
    public void testSetLogBookIdWithNegativeValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long negativeLogBookId = -1L;

        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setLogBookId(negativeLogBookId),
                "Метод setLogBookId() не кинул исключение при отрицательном значении logBookId");
    }

    /**
     * Проверяет, что метод устанавливает верное значение logBookId после изменения
     */
    @Test
    public void testSetLogBookIdAfterChange() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long initialLogBookId = 1L;
        eatingLogBook.setLogBookId(initialLogBookId);

        long newLogBookId = 2L;
        eatingLogBook.setLogBookId(newLogBookId);

        long actualLogBookId = eatingLogBook.getLogBookId();
        assertEquals(newLogBookId, actualLogBookId, "Метод setLogBookId() не установил верное значение после изменения");
    }
}
