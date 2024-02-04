package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetActivity {

    private static final String ACTIVITY_BEFORE = "Running";
    private static final String ACTIVITY_AFTER = "Swimming";

    //Проверяет работу setActivity
    @Test
    public void testSetActivity() {
        SportLogBook logBook = new SportLogBook(1, 2, 30, LocalDateTime.now(), ACTIVITY_BEFORE, "Good run");
        logBook.setActivity(ACTIVITY_AFTER);
        assertEquals(ACTIVITY_AFTER, logBook.getActivity());
    }

}
