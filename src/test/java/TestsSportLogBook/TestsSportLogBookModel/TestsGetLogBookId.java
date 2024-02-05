package TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetLogBookId {

    private static final Long LOGBOOK_ID = 1L;

    //Проверяет возвращаемое значение getLogBookId
    @Test
    public void testGetLogBookId() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, 2L, 30, LocalDateTime.now(), "Running", "Good run");
        assertEquals(LOGBOOK_ID, logBook.getLogBookId(), "getLogBookId вернул неверный результат");
    }

}
