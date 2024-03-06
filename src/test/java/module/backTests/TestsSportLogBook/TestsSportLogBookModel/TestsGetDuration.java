package module.backTests.TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetDuration {

    private static final int DURATION = 30;

    //Проверяет возвращаемое значение getDuration
    @Test
    public void testGetDuration() {
        SportLogBook logBook = new SportLogBook(1L, 2L, DURATION, LocalDateTime.now(), "Running", "Good run");
        assertEquals(DURATION, logBook.getDuration(), "getDuration вернул неверный результат");
    }

}
