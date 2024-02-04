package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест вызова метода compareTo() класса EatingLogBook
 */
public class TestEatingLogBookCompareTo {
    /**
     * Проверяет сравнение с другим объектом EatingLogBook, где время приема пищи равно
     */
    @Test
    public void testCompareToWithSameTimeEat() {
        LocalTime validTimeEat = LocalTime.of(12, 0);
        EatingLogBook eatingLogBook1 = new EatingLogBook();
        EatingLogBook eatingLogBook2 = new EatingLogBook();
        eatingLogBook1.setTimeEat(validTimeEat);
        eatingLogBook2.setTimeEat(validTimeEat);

        // Комментарий: Проверяем, что compareTo возвращает 0, если время приема пищи одинаково.
        assertEquals(0, eatingLogBook1.compareTo(eatingLogBook2), "compareTO вернул неправильное значение");
    }

    /**
     * Проверяет сравнение с другим объектом EatingLogBook, где текущий объект имеет более позднее время приема пищи
     */
    @Test
    public void testCompareToWithLaterTimeEat() {
        LocalTime earlierTimeEat = LocalTime.of(10, 0);
        LocalTime laterTimeEat = LocalTime.of(12, 0);
        EatingLogBook eatingLogBook1 = new EatingLogBook();
        EatingLogBook eatingLogBook2 = new EatingLogBook();
        eatingLogBook1.setTimeEat(earlierTimeEat);
        eatingLogBook2.setTimeEat(laterTimeEat);

        // Комментарий: Проверяем, что compareTo возвращает отрицательное значение, если текущий объект имеет более позднее время приема пищи.
        assertEquals(-1, eatingLogBook1.compareTo(eatingLogBook2), "compareTO вернул неправильное значение");
    }

    /**
     * Проверяет сравнение с другим объектом EatingLogBook, где текущий объект имеет более раннее время приема пищи
     */
    @Test
    public void testCompareToWithEarlierTimeEat() {
        LocalTime earlierTimeEat = LocalTime.of(10, 0);
        LocalTime laterTimeEat = LocalTime.of(12, 0);
        EatingLogBook eatingLogBook1 = new EatingLogBook();
        EatingLogBook eatingLogBook2 = new EatingLogBook();
        eatingLogBook1.setTimeEat(laterTimeEat);
        eatingLogBook2.setTimeEat(earlierTimeEat);

        // Комментарий: Проверяем, что compareTo возвращает положительное значение, если текущий объект имеет более раннее время приема пищи.
        assertEquals(1, eatingLogBook1.compareTo(eatingLogBook2), "compareTO вернул неправильное значение");
    }

    /**
     * Проверяет, что метод compareTo() возвращает 0 при сравнении объекта EatingLogBook самого с собой
     */
    @Test
    public void testCompareToWithSameObject() {
        LocalTime timeEat = LocalTime.of(12, 0);

        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setTimeEat(timeEat);

        int result = eatingLogBook.compareTo(eatingLogBook);

        assertEquals(0, result, "Метод compareTo() возвращает неверное значение");
    }
}
