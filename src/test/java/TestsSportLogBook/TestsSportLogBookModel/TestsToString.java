package TestsSportLogBook.TestsSportLogBookModel;

import com.cygans.database.sport_log_book.SportLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestsToString {

    private static final String NAME_CLASS = "Sport logbook";
    private static final String ID_FIELD = "id=";
    private static final String LOG_BOOK_ID_FIELD = "logBookId=";
    private static final String TIME_TYPE_FIELD = "timeType=";
    private static final String INTENSITY_ID_FIELD = "intensityId=";
    private static final String DURATION_FIELD = "duration=";
    private static final String ACTIVITY_FIELD = "activity=";
    private static final String COMMENTS_FIELD = "comments=";
    private static final Long LOGBOOK_ID = 1L;
    private static final Long INTENSITY_ID = 1L;
    private static final int DURATION = 30;
    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();
    private static final String ACTIVITY = "Running";
    private static final String COMMENTS = "Good run";
    private static final int LOGBOOK_ID_NEW = 2;
    private static final int INTENSITY_ID_NEW  = 2;
    private static final int DURATION_NEW  = 32;
    private static final String ACTIVITY_NEW  = "Swimming";
    private static final String COMMENTS_NEW  = "Bad run";
    private static final String NULL_STRING  = "null";

    /**
     * Проверяет метод toString() на наличие обязательных полей
     */
    @Test
    public void testToStringWithRequiredFields() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS);
        String toStringResult = logBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(NAME_CLASS), "Нет названия класса"),
                () -> assertTrue(toStringResult.contains(ID_FIELD), "Нет id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD), "Нет LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD), "Нет timeType"),
                () -> assertTrue(toStringResult.contains(INTENSITY_ID_FIELD), "Нет IntensityId"),
                () -> assertTrue(toStringResult.contains(DURATION_FIELD), "Нет Duration"),
                () -> assertTrue(toStringResult.contains(ACTIVITY_FIELD), "Нет Activity"),
                () -> assertTrue(toStringResult.contains(COMMENTS_FIELD), "Нет Comments")
        );
    }

    /**
     * Провеяет метод toString() с валидными значениями полей
     */
    @Test
    public void testToString() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS);
        String toStringResult = logBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(ID_FIELD + 0), "Неверное id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD +LOGBOOK_ID), "Неверное LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD + TIME_TYPE), "Неверное timeType"),
                () -> assertTrue(toStringResult.contains(INTENSITY_ID_FIELD + INTENSITY_ID), "Неверное IntensityId"),
                () -> assertTrue(toStringResult.contains(DURATION_FIELD + DURATION), "Неверное Duration"),
                () -> assertTrue(toStringResult.contains(ACTIVITY_FIELD + ACTIVITY), "Неверное Activity"),
                () -> assertTrue(toStringResult.contains(COMMENTS_FIELD + COMMENTS), "Неверное Comments")
        );
    }

    /**
     * Проверяет метод toString() после изменений полей объекта
     */
    @Test
    public void testToStringAfterModifications() {
        SportLogBook logBook = new SportLogBook(LOGBOOK_ID, INTENSITY_ID, DURATION, TIME_TYPE, ACTIVITY, COMMENTS);
        String toStringResult = logBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(ID_FIELD + 0), "Изначально неверное id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD +LOGBOOK_ID), "Изначально неверное LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD + TIME_TYPE), "Изначально неверное timeType"),
                () -> assertTrue(toStringResult.contains(INTENSITY_ID_FIELD + INTENSITY_ID), "Изначально неверное IntensityId"),
                () -> assertTrue(toStringResult.contains(DURATION_FIELD + DURATION), "Изначально неверное Duration"),
                () -> assertTrue(toStringResult.contains(ACTIVITY_FIELD + ACTIVITY), "Изначально неверное Activity"),
                () -> assertTrue(toStringResult.contains(COMMENTS_FIELD + COMMENTS), "Изначально неверное Comments")
        );

        logBook.setLogBookId(LOGBOOK_ID_NEW);
        logBook.setDuration(DURATION_NEW);
        logBook.setComments(COMMENTS_NEW);
        logBook.setActivity(ACTIVITY_NEW);
        logBook.setIntensityId(INTENSITY_ID_NEW);

        String toStringResultUpdated = logBook.toString();
        assertAll(
                () -> assertTrue(toStringResultUpdated.contains(ID_FIELD + 0), "Осталось неверное id"),
                () -> assertTrue(toStringResultUpdated.contains(LOG_BOOK_ID_FIELD + LOGBOOK_ID_NEW), "Изменение неверное LogBookId"),
                () -> assertTrue(toStringResultUpdated.contains(TIME_TYPE_FIELD + TIME_TYPE), "Осталось неверное timeType"),
                () -> assertTrue(toStringResultUpdated.contains(INTENSITY_ID_FIELD + INTENSITY_ID_NEW), "Изменение неверное IntensityId"),
                () -> assertTrue(toStringResultUpdated.contains(DURATION_FIELD + DURATION_NEW), "Изменение неверное Duration"),
                () -> assertTrue(toStringResultUpdated.contains(ACTIVITY_FIELD + ACTIVITY_NEW), "Изменение неверное Activity"),
                () -> assertTrue(toStringResultUpdated.contains(COMMENTS_FIELD + COMMENTS_NEW), "Изменение неверное Comments")
        );
    }

    /**
     * Проверяет метод toString() с пустыми значениями полей
     */
    @Test
    public void testToStringWithEmptyFields() {
        SportLogBook logBook = new SportLogBook();

        String toStringResult = logBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(ID_FIELD + 0), "неверное id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD + 0), "неверное LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD + NULL_STRING), "неверное timeType"),
                () -> assertTrue(toStringResult.contains(INTENSITY_ID_FIELD + 0), "неверное IntensityId"),
                () -> assertTrue(toStringResult.contains(DURATION_FIELD + 0), "неверное Duration"),
                () -> assertTrue(toStringResult.contains(ACTIVITY_FIELD + NULL_STRING), "неверное Activity"),
                () -> assertTrue(toStringResult.contains(COMMENTS_FIELD + NULL_STRING), "неверное Comments")
        );
    }

}
