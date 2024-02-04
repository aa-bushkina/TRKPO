package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetDuration {

    private static final int DURATION_BEFORE = 30;
    private static final int DURATION_AFTER = 10;

    //Проверяет работу setDuration
    @Test
    public void testSetDuration() {
        SportLogBook logBook = new SportLogBook(1, 2, DURATION_BEFORE, LocalDateTime.now(), "Running", "Good run");
        logBook.setDuration(DURATION_AFTER);
        assertEquals(DURATION_AFTER, logBook.getDuration());
    }

}
