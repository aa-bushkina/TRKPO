package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetLogBookId {

    private static final int LOGBOOK_ID = 1;

    //Проверяет возвращаемое значение getLogBookId
    @Test
    public void testGetLogBookId() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, 2, 30, LocalDateTime.now(), "Running", "Good run");
        assertEquals(LOGBOOK_ID, logBook.getLogBookId());
    }

}
