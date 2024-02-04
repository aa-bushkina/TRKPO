package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для метода setTimeType() класса EatingLogBook
 */
public class TestEatingLogBookSetTimeType {
    /**
     * Проверяет, что метод корректно устанавливает timeType
     */
    @Test
    public void testSetTimeTypeWithValidValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        LocalDateTime expectedTimeType = LocalDateTime.now();

        eatingLogBook.setTimeType(expectedTimeType);

        LocalDateTime actualTimeType = eatingLogBook.getTimeType();

        assertEquals(expectedTimeType, actualTimeType, "Метод setTimeType() не установил корректное значение");
    }

    /**
     * Проверяет, что метод корректно устанавливает новое значение timeType после изменения
     */
    @Test
    public void testSetTimeTypeAfterChange() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        LocalDateTime initialTimeType = LocalDateTime.now();
        eatingLogBook.setTimeType(initialTimeType);

        LocalDateTime newTimeType = LocalDateTime.now().plusDays(1);
        eatingLogBook.setTimeType(newTimeType);

        LocalDateTime actualTimeType = eatingLogBook.getTimeType();

        assertEquals(newTimeType, actualTimeType, "Метод setTimeType() не установил корректное новое значение");
    }

    /**
     * Проверяет, что метод кидает исключение, если передан null
     */
    @Test
    public void testSetTimeTypeWithNullValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setTimeType(null),
                "Метод setTimeType() должен кидать исключение, если передан null");
    }
}
