package backTests.TestsEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestToString {

    private static final String NAME_CLASS = "Emotional logbook";
    private static final String ID_FIELD = "id=";
    private static final String LOG_BOOK_ID_FIELD = "logBookId=";
    private static final String TIME_TYPE_FIELD = "timeType=";
    private static final String DESCRIPTION_FIELD = "description=";
    private static final Long LOGBOOK_ID = 1L;
    private static final LocalDateTime TIME_TYPE = LocalDateTime.now();
    private static final LocalDateTime TIME_TYPE_MEW = TIME_TYPE.plusDays(5);
    private static final Long LOGBOOK_ID_NEW = 2L;
    private static final String NULL_STRING = "null";
    private static final String DESCRIPTION_STRING = "Emotional text";
    private static final String DESCRIPTION_STRING_NEW = "Too emotional text";

    /**
     * Проверяет метод toString() на наличие обязательных полей
     */
    @Test
    public void testToStringWithRequiredFields() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(LOGBOOK_ID, TIME_TYPE, DESCRIPTION_STRING);
        String toStringResult = emotionalLogBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(NAME_CLASS), "Нет названия класса"),
                () -> assertTrue(toStringResult.contains(ID_FIELD), "Нет id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD), "Нет LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD), "Нет timeType"),
                () -> assertTrue(toStringResult.contains(DESCRIPTION_FIELD), "Нет description")
        );
    }

    /**
     * Провеяет метод toString() с валидными значениями полей
     */
    @Test
    public void testToString() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(LOGBOOK_ID, TIME_TYPE, DESCRIPTION_STRING);
        String toStringResult = emotionalLogBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(ID_FIELD + 0), "Неверное id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD + LOGBOOK_ID), "Неверное LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD + TIME_TYPE), "Неверное timeType"),
                () -> assertTrue(toStringResult.contains(DESCRIPTION_FIELD + DESCRIPTION_STRING), "Неверное description")
        );
    }

    /**
     * Проверяет метод toString() после изменений полей объекта
     */
    @Test
    public void testToStringAfterModifications() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(LOGBOOK_ID, TIME_TYPE, DESCRIPTION_STRING);

        emotionalLogBook.setLogBookId(LOGBOOK_ID_NEW);
        emotionalLogBook.setTimeType(TIME_TYPE_MEW);
        emotionalLogBook.setDescription(DESCRIPTION_STRING_NEW);

        String toStringResultUpdated = emotionalLogBook.toString();
        assertAll(
                () -> assertTrue(toStringResultUpdated.contains(ID_FIELD + 0), "Неверное id"),
                () -> assertTrue(toStringResultUpdated.contains(LOG_BOOK_ID_FIELD + LOGBOOK_ID_NEW), "Неверное LogBookId"),
                () -> assertTrue(toStringResultUpdated.contains(TIME_TYPE_FIELD + TIME_TYPE_MEW), "Неверное timeType"),
                () -> assertTrue(toStringResultUpdated.contains(DESCRIPTION_FIELD + DESCRIPTION_STRING_NEW), "Неверное description")
        );
    }

    /**
     * Проверяет метод toString() с пустыми значениями полей
     */
    @Test
    public void testToStringWithEmptyFields() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();

        String toStringResult = emotionalLogBook.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(ID_FIELD + 0), "Неверное id"),
                () -> assertTrue(toStringResult.contains(LOG_BOOK_ID_FIELD + NULL_STRING), "Неверное LogBookId"),
                () -> assertTrue(toStringResult.contains(TIME_TYPE_FIELD + NULL_STRING), "Неверное timeType"),
                () -> assertTrue(toStringResult.contains(DESCRIPTION_FIELD + NULL_STRING), "Неверное description")
        );
    }

}
