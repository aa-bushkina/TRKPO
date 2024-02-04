package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEatingLogBookSetId {
    /**
     * Проверяет, что метод корректно устанавливает id
     */
    @Test
    public void testSetId() {
        long expectedId = 1L;
        EatingLogBook eatingLogBook = new EatingLogBook();

        eatingLogBook.setId(expectedId);

        assertEquals(expectedId, eatingLogBook.getId(), "Метод setId() не установил правильное значение");
    }

    /**
     * Проверяет, что метод корректно устанавливает id в 0
     */
    @Test
    public void testSetIdWithZeroValue() {
        long zeroId = 0L;
        EatingLogBook eatingLogBook = new EatingLogBook();

        eatingLogBook.setId(zeroId);

        assertEquals(zeroId, eatingLogBook.getId(), "Метод setId() не установил правильное значение");
    }

    /**
     * Проверяет, что метод кидает исключение при отрицательном значение id
     */
    @Test
    public void testSetIdWithNegativeValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setId(-1),
                "Метод должен бросить исключение для отрицательных значений id");
    }

    /**
     * Проверяет, что метод корректно устанавливает новое значение id после уже установленного
     */
    @Test
    public void testSetIdAfterInitialValue() {
        long initialId = 1L;
        long newId = 2L;
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setId(initialId);

        eatingLogBook.setId(newId);

        assertEquals(newId, eatingLogBook.getId(), "Метод setId() не установил правильное значение после изменения");
    }

    /**
     * Проверяет, что метод корректно устанавливает максимальное значение id
     */
    @Test
    public void testSetIdWithMaxValue() {
        long maxId = Long.MAX_VALUE;
        EatingLogBook eatingLogBook = new EatingLogBook();

        eatingLogBook.setId(maxId);

        assertEquals(maxId, eatingLogBook.getId(), "Метод setId() не установил правильное максимальное значение");
    }
}
