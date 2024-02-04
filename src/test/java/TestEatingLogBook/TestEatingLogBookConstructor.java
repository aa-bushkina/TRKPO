package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тест проверяет вызов конструкторов класса EatingLogBook
 */
public class TestEatingLogBookConstructor {
    /**
     * Проверяет вызов конструктора без параметров
     */
    @Test
    public void testDefaultConstructor() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        assertNotNull(eatingLogBook.getId(), "Id не должен быть null");
        assertEquals(0, eatingLogBook.getLogBookId(), "LogBookId должен быть равен 0");
        assertNull(eatingLogBook.getTimeEat(), "TimeEat должен быть null");
        assertNull(eatingLogBook.getDescription(), "Description должен быть null");
        assertEquals(0, eatingLogBook.getMealId(), "MealId должен быть равен 0");
        assertNull(eatingLogBook.getTimeType(), "TimeType должен быть null");
    }

    /**
     * Проверяет вызов конструктора с валидными параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        long logBookId = 1L;
        LocalTime timeEat = LocalTime.NOON;
        String description = "Test description";
        long mealId = 2L;
        LocalDateTime timeType = LocalDateTime.now();

        EatingLogBook eatingLogBook = new EatingLogBook(logBookId, timeEat, description, mealId, timeType);

        assertNotNull(eatingLogBook.getId(), "Id не должен быть null");
        assertEquals(logBookId, eatingLogBook.getLogBookId(), "LogBookId не соответствует ожидаемому значению");
        assertEquals(timeEat, eatingLogBook.getTimeEat(), "TimeEat не соответствует ожидаемому значению");
        assertEquals(description, eatingLogBook.getDescription(), "Description не соответствует ожидаемому значению");
        assertEquals(mealId, eatingLogBook.getMealId(), "MealId не соответствует ожидаемому значению");
        assertEquals(timeType, eatingLogBook.getTimeType(), "TimeType не соответствует ожидаемому значению");
    }

    /**
     * Проверяет вызов конструктора, когда все параметры конструктора равны null или пусты
     */
    @Test
    public void testConstructorWithEmptyValues() {
        assertThrows(IllegalArgumentException.class, () -> new EatingLogBook(0, null, "", 0, null), "Конструктор должен бросить исключение для пустых значений полей");
    }

    /**
     * Проверяет вызов конструктора  с отрицательными значениями параметров
     */
    @Test
    public void testConstructorWithNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> new EatingLogBook(-1, LocalTime.NOON, "Test", -2, LocalDateTime.now()), "Конструктор должен бросить исключение для отрицательных значений LogBookId и MealId");
    }

    /**
     * Проверяет вызов конструктора с большими значениями параметров
     */
    @Test
    public void testConstructorWithLargeValues() {
        LocalDateTime timeType = LocalDateTime.now();
        EatingLogBook eatingLogBook = new EatingLogBook(Long.MAX_VALUE, LocalTime.NOON, "Test", Long.MAX_VALUE, timeType);

        assertNotNull(eatingLogBook.getId(), "Id не должен быть null");
        assertEquals(Long.MAX_VALUE, eatingLogBook.getLogBookId(), "LogBookId не соответствует ожидаемому значению");
        assertEquals(LocalTime.NOON, eatingLogBook.getTimeEat(), "TimeEat не соответствует ожидаемому значению");
        assertEquals("Test", eatingLogBook.getDescription(), "Description не соответствует ожидаемому значению");
        assertEquals(Long.MAX_VALUE, eatingLogBook.getMealId(), "MealId не соответствует ожидаемому значению");
        assertEquals(timeType, eatingLogBook.getTimeType(), "TimeType не соответствует ожидаемому значению");
    }
}
