package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsConstructor {

    private static final int LOGBOOK_ID = 1;
    private static final int INTENSITY_ID = 1;
    private static final int DURATION = 30;
    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();
    private static final String ACTIVITY = "Running";
    private static final String COMMENTS = "Good run";

    //Проверяет работу конструктора
    @Test
    public void testConstructor() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS);
        assertAll(
                () -> assertEquals(LOGBOOK_ID, logBook.getLogBookId()),
                () -> assertEquals(INTENSITY_ID, logBook.getIntensityId()),
                () -> assertEquals(DURATION, logBook.getDuration()),
                () -> assertEquals(TIME_TYPE, logBook.getTimeType()),
                () -> assertEquals(ACTIVITY, logBook.getActivity()),
                () -> assertEquals(COMMENTS, logBook.getComments())
        );
    }

}
