package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsToString {

    private static final int LOGBOOK_ID = 1;
    private static final int INTENSITY_ID = 1;
    private static final int DURATION = 30;
    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();
    private static final String ACTIVITY = "Running";
    private static final String COMMENTS = "Good run";

    //Проверяет работу метода toString
    @Test
    public void testToString() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS);
        String expected = "Sport logbook{id=0,logBookId=" + logBook.getLogBookId() +
                ", timeType=" + logBook.getTimeType() +
                ", intensityId=" + logBook.getIntensityId() +
                ", duration=" + logBook.getDuration() +
                ", activity=" + logBook.getActivity() +
                ", comments=" + logBook.getComments() + "}";
        assertEquals(expected, logBook.toString());
    }

}
