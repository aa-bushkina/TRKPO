package TestEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetLogBookId {

    private static final Long LOGBOOK_ID = 1L;

    //Проверяет возвращаемое значение getLogBookId
    @Test
    public void testGetLogBookId() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(LOGBOOK_ID, LocalDateTime.now(), "Emotional text");
        assertEquals(LOGBOOK_ID, emotionalLogBook.getLogBookId(), "getLogBookId вернул неверный результат");
    }

}
