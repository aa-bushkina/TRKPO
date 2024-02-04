package TestEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetLogBookId {

    private static final Long LOGBOOK_ID_BEFORE = 1L;
    private static final Long LOGBOOK_ID_AFTER = 2L;

    //Проверяет работу setLogBookId
    @Test
    public void testSetLogBookId() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(LOGBOOK_ID_BEFORE, LocalDateTime.now(), "Emotional text");
        emotionalLogBook.setLogBookId(LOGBOOK_ID_AFTER);
        assertEquals(LOGBOOK_ID_AFTER, emotionalLogBook.getLogBookId(), "setLogBookId установил неверное значение");
    }

}
