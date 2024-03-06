package module.backTests.TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetComments {

    private static final String COMMENTS_BEFORE = "Good run";
    private static final String COMMENTS_AFTER = "Bad run";

    //Проверяет работу setComments
    @Test
    public void testSetComments() {
        SportLogBook logBook = new SportLogBook(1L, 2L, 30, LocalDateTime.now(), "Running", COMMENTS_BEFORE);
        logBook.setComments(COMMENTS_AFTER);
        assertEquals(COMMENTS_AFTER, logBook.getComments(), "setComments установил неверное значение");
    }

}
