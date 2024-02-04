package TestLogBook.TestLog;

import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет вызов метода compareTo() в классе Log
 */
public class TestLogCompareTo {
    /**
     * Проверяет, что метод возвращает 0 для логов с одинаковой датой
     */
    @Test
    public void testCompareToEqualDates() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        Log log1 = new Log(null, date, 1L);
        Log log2 = new Log(null, date, 2L);

        assertEquals(0, log1.compareTo(log2), "Метод compareTo() не возвращает 0 для логов с одинаковой датой");
    }

    /**
     * Проверяет, что метод возвращает отрицательное значение для лога с более ранней датой
     */
    @Test
    public void testCompareToEarlierDate() {
        LocalDate earlierDate = LocalDate.of(2022, 1, 1);
        LocalDate laterDate = LocalDate.of(2022, 2, 1);
        Log log1 = new Log(null, earlierDate, 1L);
        Log log2 = new Log(null, laterDate, 2L);

        assertTrue(log1.compareTo(log2) < 0, "Метод compareTo() не возвращает отрицательное значение для лога с более ранней датой");
    }

    /**
     * Проверяет, что метод возвращает положительное значение для лога с более поздней датой
     */
    @Test
    public void testCompareToLaterDate() {
        LocalDate earlierDate = LocalDate.of(2022, 1, 1);
        LocalDate laterDate = LocalDate.of(2022, 2, 1);
        Log log1 = new Log(null, earlierDate, 1L);
        Log log2 = new Log(null, laterDate, 2L);

        assertTrue(log2.compareTo(log1) > 0, "Метод compareTo() не возвращает положительное значение для лога с более поздней датой");
    }
}
