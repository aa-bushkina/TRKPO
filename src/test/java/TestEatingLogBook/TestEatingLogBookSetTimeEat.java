package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для метода setTimeEat() класса EatingLogBook
 */
public class TestEatingLogBookSetTimeEat {
    /**
     * Проверяет, что метод корректно устанавливает непустое значение timeEat
     */
    @Test
    public void testSetTimeEatWithValidValue() {
        LocalTime validTimeEat = LocalTime.NOON;
        EatingLogBook eatingLogBook = new EatingLogBook();

        eatingLogBook.setTimeEat(validTimeEat);

        assertEquals(validTimeEat, eatingLogBook.getTimeEat(), "Метод setTimeEat() не установил правильное значение");
    }

    /**
     * Проверяет, что метод выбрасывает IllegalArgumentException при передаче значения null
     */
    @Test
    public void testSetTimeEatWithNullValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setTimeEat(null),
                "Метод setTimeEat() должен выбрасывать IllegalArgumentException при передаче значения null");
    }
}
