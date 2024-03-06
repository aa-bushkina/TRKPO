package module.backTests.TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetLogBookId {

    private static final Long LOGBOOK_ID_BEFORE = 1L;
    private static final Long LOGBOOK_ID_AFTER = 2L;

    //Проверяет работу setLogBookId
    @Test
    public void testSetLogBookId() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID_BEFORE, 2L, 30, LocalDateTime.now(), "Running", "Good run");
        logBook.setLogBookId(LOGBOOK_ID_AFTER);
        assertEquals(LOGBOOK_ID_AFTER, logBook.getLogBookId(), "setLogBookId установил неверное значение");
    }

}
