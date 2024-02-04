package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetIntensityId {

    private static final Long INTENSITY_ID = 1L;

    //Проверяет возвращаемое значение getIntensityId
    @Test
    public void testGetIntensityId() {
        SportLogBook logBook = new SportLogBook(1L, INTENSITY_ID, 30, LocalDateTime.now(), "Running", "Good run");
        assertEquals(INTENSITY_ID, logBook.getIntensityId(), "getIntensityId вернул неверный результат");
    }

}
