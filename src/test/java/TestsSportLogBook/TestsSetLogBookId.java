package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetLogBookId {

    private static final int LOGBOOK_ID_BEFORE = 1;
    private static final int LOGBOOK_ID_AFTER = 2;

    //Проверяет работу setLogBookId
    @Test
    public void testSetLogBookId() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID_BEFORE, 2, 30, LocalDateTime.now(), "Running", "Good run");
        logBook.setLogBookId(LOGBOOK_ID_AFTER);
        assertEquals(LOGBOOK_ID_AFTER, logBook.getLogBookId());
    }

}
