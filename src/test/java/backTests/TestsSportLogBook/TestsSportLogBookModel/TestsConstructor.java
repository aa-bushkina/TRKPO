package backTests.TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsConstructor {

    private static final Long LOGBOOK_ID = 1L;
    private static final Long INTENSITY_ID = 1L;
    private static final int DURATION = 30;
    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();
    private static final String ACTIVITY = "Running";
    private static final String COMMENTS = "Good run";


    /***
     * Проверяет работу конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS);
        assertAll(
                () -> assertEquals(LOGBOOK_ID, logBook.getLogBookId(), "LogBookId неверное"),
                () -> assertEquals(INTENSITY_ID, logBook.getIntensityId(), "IntensityId неверное"),
                () -> assertEquals(DURATION, logBook.getDuration(), "Duration неверное"),
                () -> assertEquals(TIME_TYPE, logBook.getTimeType(), "TimeType неверное"),
                () -> assertEquals(ACTIVITY, logBook.getActivity(), "Activity неверное"),
                () -> assertEquals(COMMENTS, logBook.getComments(), "Comments неверное")
        );
    }

    /***
     * Проверяет работу конструктора без параметров
     */
    @Test
    public void testConstructorWithoutParameters() {
        SportLogBook logBook = new SportLogBook();
        assertAll(
                () -> assertEquals(0, logBook.getLogBookId(), "LogBookId не пустое"),
                () -> assertEquals(0, logBook.getIntensityId(), "IntensityId не пустое"),
                () -> assertEquals(0, logBook.getDuration(), "Duration не пустое"),
                () -> assertNull(logBook.getTimeType(), "TimeType не пустое"),
                () -> assertNull(logBook.getActivity(), "Activity не пустое"),
                () -> assertNull(logBook.getComments(), "Comments не пустое")
        );
    }

    /**
     * Проверяет конструктор с logBookId null
     */
    @Test
    public void testConstructorWithLogBookIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(null, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS),
                "Не получили ожидаеме исключение при вызове метода с logBookId null");
    }

    /**
     * Проверяет конструктор с intensityId null
     */
    @Test
    public void testConstructorWithIntensityIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, null, DURATION, TIME_TYPE, ACTIVITY, COMMENTS),
                "Не получили ожидаемое исключение при вызове метода c intensityId null");
    }

    /**
     * Проверяет конструктор с duration null
     */
    @Test
    public void testConstructorWithDurationNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, INTENSITY_ID, null, TIME_TYPE, ACTIVITY, COMMENTS),
                "Не получили ожидаемое исключение при вызове метода c duration null");
    }

    /**
     * Проверяет конструктор с timeType null
     */
    @Test
    public void testConstructorWithTimeTypeNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, null, ACTIVITY, COMMENTS),
                "Не получили ожидаемое исключение при вызове метода c timeType null");
    }

    /**
     * Проверяет конструктор с activity null
     */
    @Test
    public void testConstructorWithActivityNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, null, COMMENTS),
                "Не получили ожидаемое исключение при вызове метода c activity null");
    }

    /**
     * Проверяет конструктор с comments null
     */
    @Test
    public void testConstructorWithCommentsNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, null),
                "Не получили ожидаемое исключение при вызове метода c comments null");
    }


    /**
     * Проверяет конструктор с пустым activity
     */
    @Test
    public void testConstructorWithActivityEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, "", COMMENTS),
                "Не получили ожидаемое исключение при вызове метода c пустым activity");
    }

    /**
     * Проверяет конструктор с comments null
     */
    @Test
    public void testConstructorWithCommentsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, ""),
                "Не получили ожидаемое исключение при вызове метода c пустым comments");
    }

}
