package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetComments {

    private static final String COMMENTS = "Good run";

    //Проверяет возвращаемое значение getComments
    @Test
    public void tesGetComments() {
        SportLogBook logBook = new SportLogBook(1, 2, 30, LocalDateTime.now(), "Running", COMMENTS);
        assertEquals(COMMENTS, logBook.getComments(), "getComments вернул неверный результат");
    }

}
