package TestEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetDescription {

    private static final String DESCRIPTION = "Emotional text";

    //Проверяет возвращаемое значение emotionalText
    @Test
    public void testGetDescription() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(1L, LocalDateTime.now(), DESCRIPTION);
        assertEquals(DESCRIPTION, emotionalLogBook.getDescription(), "getDescription() вернул неверный результат");
    }

}
