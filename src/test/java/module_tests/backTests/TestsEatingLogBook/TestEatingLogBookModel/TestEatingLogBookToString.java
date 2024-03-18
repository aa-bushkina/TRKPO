package module_tests.backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест вызова метода toString() класса EatingLogBook
 */
public class TestEatingLogBookToString {
    /**
     * Проверяет, что метод toString() возвращает строку с корректными значениями полей
     */
    @Test
    public void testToString() {
        long id = 1L;
        long logBookId = 2L;
        LocalTime timeEat = LocalTime.NOON;
        String description = "Test description";
        long mealId = 3L;
        LocalDateTime timeType = LocalDateTime.now();

        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setId(id);
        eatingLogBook.setLogBookId(logBookId);
        eatingLogBook.setTimeEat(timeEat);
        eatingLogBook.setDescription(description);
        eatingLogBook.setMealId(mealId);
        eatingLogBook.setTimeType(timeType);

        String expectedString = "Eating logbook{" +
                "id=" + id +
                ", logBookId=" + logBookId +
                ", timeEat=" + timeEat +
                ", description=" + description +
                ", mealId=" + mealId +
                ", timeType=" + timeType +
                "}";

        assertEquals(expectedString, eatingLogBook.toString(), "Метод toString() возвращает неверную строку");
    }

    /**
     * Проверяет, что метод toString() возвращает строку с корректными значениями полей, когда они null
     */
    @Test
    public void testToStringWithNullValues() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        String expectedString = "Eating logbook{" +
                "id=0" +
                ", logBookId=0" +
                ", timeEat=null" +
                ", description=null" +
                ", mealId=0" +
                ", timeType=null" +
                "}";

        assertEquals(expectedString, eatingLogBook.toString(), "Метод toString() возвращает неверную строку");
    }

    /**
     * Проверяет вызов метода toString() после изменения значений полей
     */
    @Test
    public void testToStringAfterFieldChanges() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        String expectedString = "Eating logbook{" +
                "id=0" +
                ", logBookId=0" +
                ", timeEat=null" +
                ", description=null" +
                ", mealId=0" +
                ", timeType=null" +
                "}";
        assertEquals(expectedString, eatingLogBook.toString(), "Метод toString() возвращает неверную строку");

        eatingLogBook.setId(4L);
        eatingLogBook.setLogBookId(5L);
        eatingLogBook.setTimeEat(LocalTime.MIDNIGHT);
        eatingLogBook.setDescription("New description");
        eatingLogBook.setMealId(6L);
        eatingLogBook.setTimeType(LocalDateTime.now().plusDays(1));

        String expectedUpdatedString = "Eating logbook{" +
                "id=4" +
                ", logBookId=5" +
                ", timeEat=00:00" +
                ", description=New description" +
                ", mealId=6" +
                ", timeType=" + eatingLogBook.getTimeType() +
                "}";

        assertEquals(expectedUpdatedString, eatingLogBook.toString(), "Метод toString() вернул неверное значение после изменения полей");
    }
}
