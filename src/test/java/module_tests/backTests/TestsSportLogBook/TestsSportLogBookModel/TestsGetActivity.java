package module_tests.backTests.TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetActivity {

    private static final String ACTIVITY = "Running";

    //Проверяет возвращаемое значение getActivity
    @Test
    public void testGetActivity() {
        SportLogBook logBook = new SportLogBook(1L, 2L, 30, LocalDateTime.now(), ACTIVITY, "Good run");
        assertEquals(ACTIVITY, logBook.getActivity(), "getActivity вернул неверный результат");
    }

}
