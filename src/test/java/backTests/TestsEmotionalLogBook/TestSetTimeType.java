package backTests.TestsEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetTimeType {

    private static final LocalDateTime TIME_TYPE_BEFORE = LocalDateTime.now();
    private static final LocalDateTime TIME_TYPE_AFTER = TIME_TYPE_BEFORE.plusHours(10);

    //Проверяет работу setLogBookId
    @Test
    public void testSetLogBookId() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(1L, TIME_TYPE_BEFORE, "Emotional text");
        emotionalLogBook.setTimeType(TIME_TYPE_AFTER);
        assertEquals(TIME_TYPE_AFTER, emotionalLogBook.getTimeType(), "setTimeType установил неверное значение");
    }
}
