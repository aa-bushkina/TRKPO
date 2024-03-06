package module.backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для метода getTimeEat() класса EatingLogBook
 */
public class TestEatingLogBookGetTimeEat {
    /**
     * Проверяет, что метод возвращает корректное значение timeEat
     */
    @Test
    public void testGetTimeEat() {
        LocalTime expectedTimeEat = LocalTime.NOON;
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setTimeEat(expectedTimeEat);

        LocalTime actualTimeEat = eatingLogBook.getTimeEat();

        assertEquals(expectedTimeEat, actualTimeEat, "Метод getTimeEat() возвращает неверное значение");
    }

    /**
     * Проверяет, что метод возвращает null, если timeEat не установлено
     */
    @Test
    public void testGetTimeEatWithNullValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        assertNull(eatingLogBook.getTimeEat(), "Метод getTimeEat() должен возвращать null, если timeEat не установлено");
    }
}
