package TestEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCompareTo {


    private static final LocalDateTime TIME_TYPE_1 = LocalDateTime.now();
    private static final LocalDateTime TIME_TYPE_2 = TIME_TYPE_1.plusHours(1);

    private static final String DESCRIPTION = "Emotional text";

    //Сравнение, когда у вызывающий объект меньше
    @Test
    public void testCompareToFirstLess() {

        EmotionalLogBook emotionalLogBook1 = new EmotionalLogBook(1L, TIME_TYPE_1, DESCRIPTION);
        EmotionalLogBook emotionalLogBook2 = new EmotionalLogBook(2L, TIME_TYPE_2, DESCRIPTION);

        assertTrue(emotionalLogBook1.compareTo(emotionalLogBook2) < 0, "compareTo вернул неверный результат");
    }

    //Сравнение, когда у вызывающий объект больше
    @Test
    public void testCompareToFirstMore() {
        EmotionalLogBook emotionalLogBook1 = new EmotionalLogBook(1L, TIME_TYPE_1, DESCRIPTION);
        EmotionalLogBook emotionalLogBook2 = new EmotionalLogBook(2L, TIME_TYPE_2, DESCRIPTION);

        assertTrue(emotionalLogBook2.compareTo(emotionalLogBook1) > 0, "compareTo вернул неверный результат");
    }

    //Сравнение, когда объекты равны
    @Test
    public void testCompareToEquals() {
        EmotionalLogBook emotionalLogBook1 = new EmotionalLogBook(1L, TIME_TYPE_1, DESCRIPTION);
        EmotionalLogBook emotionalLogBook2 = new EmotionalLogBook(2L, TIME_TYPE_1, DESCRIPTION);
        assertEquals(0, emotionalLogBook1.compareTo(emotionalLogBook2), "compareTo вернул неверный результат");
    }

}
