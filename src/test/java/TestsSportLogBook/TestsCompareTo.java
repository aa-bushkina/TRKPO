package TestsSportLogBook;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsCompareTo {


    private static final LocalDateTime TIME_TYPE_1 = LocalDateTime.now();
    private static final  LocalDateTime TIME_TYPE_2 = TIME_TYPE_1.plusHours(1);

    //Сравнение, когда у вызывающий объект меньше
    @Test
    public void testCompareToFirstLess() {
        SportLogBook logBook1 = new SportLogBook(1, 2, 30, TIME_TYPE_1, "Running", "Good run");
        SportLogBook logBook2 = new SportLogBook(2, 1, 32, TIME_TYPE_2, "Swimming", "Bad run");
        assertTrue(logBook1.compareTo(logBook2) < 0);
    }

    //Сравнение, когда у вызывающий объект больше
    @Test
    public void testCompareToFirstMore() {
        SportLogBook logBook1 = new SportLogBook(1, 2, 30, TIME_TYPE_1, "Running", "Good run");
        SportLogBook logBook2 = new SportLogBook(2, 1, 32, TIME_TYPE_2, "Swimming", "Bad run");
        assertTrue(logBook2.compareTo(logBook1) > 0);
    }

    //Сравнение, когда объекты равны
    @Test
    public void testCompareToEquals() {
        SportLogBook logBook1 = new SportLogBook(1, 2, 30, TIME_TYPE_1, "Running", "Good run");
        SportLogBook logBook2 = new SportLogBook(2, 1, 32, TIME_TYPE_1, "Swimming", "Bad run");
        assertEquals(0, logBook1.compareTo(logBook2));
    }

}
