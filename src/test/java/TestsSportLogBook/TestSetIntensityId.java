package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetIntensityId {

    private static final int INTENSITY_ID_BEFORE = 1;
    private static final int INTENSITY_ID_AFTER = 2;

    //Проверяет работу setIntensityId
    @Test
    public void testSetIntensityId() {
        SportLogBook logBook = new SportLogBook(1, INTENSITY_ID_BEFORE, 30, LocalDateTime.now(), "Running", "Good run");
        logBook.setIntensityId(INTENSITY_ID_AFTER);
        assertEquals(INTENSITY_ID_AFTER, logBook.getIntensityId());
    }

}
