package backTests.TestsEmotionalLogBook.TestsEatingLogBookModel;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetDescription {

    private static final String DESCRIPTION_BEFORE = "Emotional text";
    private static final String DESCRIPTION_AFTER = "Too emotional text";

    //Проверяет работу setDescription
    @Test
    public void testSetLogBookId() {

        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(1L, LocalDateTime.now(), DESCRIPTION_BEFORE);
        emotionalLogBook.setDescription(DESCRIPTION_AFTER);
        assertEquals(DESCRIPTION_AFTER, emotionalLogBook.getDescription(), "setLogBookId установил неверное значение");
    }

}
