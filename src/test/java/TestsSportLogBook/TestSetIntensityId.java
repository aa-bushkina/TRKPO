package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetIntensityId {

    private static final Long INTENSITY_ID_BEFORE = 1L;
    private static final Long INTENSITY_ID_AFTER = 2L;

    //Проверяет работу setIntensityId
    @Test
    public void testSetIntensityId() {
        SportLogBook logBook = new SportLogBook(1L, INTENSITY_ID_BEFORE, 30, LocalDateTime.now(), "Running", "Good run");
        logBook.setIntensityId(INTENSITY_ID_AFTER);
        assertEquals(INTENSITY_ID_AFTER, logBook.getIntensityId(), "setIntensityId установил неверное значение");
    }

}
