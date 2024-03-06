package module.backTests.TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetTimeType {

    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();

    //Проверяет возвращаемое значение getTimeType
    @Test
    public void testGetTimeType() {
        SportLogBook logBook = new SportLogBook(1L, 2L, 30, TIME_TYPE, "Running", "Good run");
        assertEquals(TIME_TYPE, logBook.getTimeType(), "getTimeType вернул неверный результат");
    }

}
