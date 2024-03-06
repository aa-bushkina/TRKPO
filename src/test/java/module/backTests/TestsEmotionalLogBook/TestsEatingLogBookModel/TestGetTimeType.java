package module.backTests.TestsEmotionalLogBook.TestsEatingLogBookModel;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetTimeType {

    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();

    //Проверяет возвращаемое значение getTimeType
    @Test
    public void testGetTimeType() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(1L, TIME_TYPE, "Emotional text");
        assertEquals(TIME_TYPE, emotionalLogBook.getTimeType(), "getTimeType вернул неверный результат");
    }

}
