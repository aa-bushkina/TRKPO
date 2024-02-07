package backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

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
    private static final long LOG_BOOK_ID = 1L;
    private static final LocalTime TIME_EAT = LocalTime.NOON;
    private static final String DESCRIPTION = "Test description";
    private static final long MEAL_ID = 2L;

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
        LocalDateTime timeType = LocalDateTime.now();

        EatingLogBook eatingLogBook = new EatingLogBook(LOG_BOOK_ID, TIME_EAT, DESCRIPTION, MEAL_ID, timeType);

        assertNotNull(eatingLogBook.getId(), "Id не должен быть null");
        assertEquals(LOG_BOOK_ID, eatingLogBook.getLogBookId(), "LogBookId не соответствует ожидаемому значению");
        assertEquals(TIME_EAT, eatingLogBook.getTimeEat(), "TimeEat не соответствует ожидаемому значению");
        assertEquals(DESCRIPTION, eatingLogBook.getDescription(), "Description не соответствует ожидаемому значению");
        assertEquals(MEAL_ID, eatingLogBook.getMealId(), "MealId не соответствует ожидаемому значению");
        assertEquals(timeType, eatingLogBook.getTimeType(), "TimeType не соответствует ожидаемому значению");
    }

    /**
     * Проверяет вызов конструктора, когда все параметры конструктора равны null или пусты
     */
    @Test
    public void testConstructorWithEmptyValues() {
        assertThrows(IllegalArgumentException.class, () -> new EatingLogBook(0, null, "", 0, null),
                "Конструктор должен бросить исключение для пустых значений полей");
    }

    /**
     * Проверяет конструктор с параметром MealId = null
     */
    @Test
    public void testConstructorWithNullMealId() {
        LocalDateTime timeType = LocalDateTime.now();
        assertThrows(IllegalArgumentException.class, () -> new EatingLogBook(LOG_BOOK_ID, TIME_EAT, DESCRIPTION, -1, timeType),
                "Не получили ожидаеме исключение при вызове метода с параметром MealId = null");
    }

    /**
     * Проверяет конструктор с параметром TimeType = null
     */
    @Test
    public void testConstructorWithNullTimeType() {
        assertThrows(IllegalArgumentException.class, () -> new EatingLogBook(LOG_BOOK_ID, TIME_EAT, DESCRIPTION, MEAL_ID, null),
                "Не получили ожидаеме исключение при вызове метода с параметром TimeType = null");
    }

    /**
     * Проверяет вызов конструктора  с отрицательными значениями параметров
     */
    @Test
    public void testConstructorWithNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> new EatingLogBook(-1, LocalTime.NOON, "Test", -2, LocalDateTime.now()),
                "Конструктор должен бросить исключение для отрицательных значений LogBookId и MealId");
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
