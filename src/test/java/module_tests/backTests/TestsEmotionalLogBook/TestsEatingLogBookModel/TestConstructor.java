package module_tests.backTests.TestsEmotionalLogBook.TestsEatingLogBookModel;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstructor {

    private static final Long LOG_BOOK_ID = 5L;
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final String DESCRIPTION = "Emotional text.";

    private static Stream<Arguments> provideInvalidParams() {
        return Stream.of(
                Arguments.of(null, LOCAL_DATE_TIME, DESCRIPTION),
                Arguments.of(LOG_BOOK_ID, LOCAL_DATE_TIME, null),
                Arguments.of(LOG_BOOK_ID, null, DESCRIPTION),
                Arguments.of(null, null, null)
        );
    }

    /**
     * Проверяет конструктор с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(LOG_BOOK_ID, LOCAL_DATE_TIME, DESCRIPTION);

        assertAll(
                () -> assertEquals(LOG_BOOK_ID, emotionalLogBook.getLogBookId()),
                () -> assertEquals(LOCAL_DATE_TIME, emotionalLogBook.getTimeType()),
                () -> assertEquals(DESCRIPTION, emotionalLogBook.getDescription())
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();

        assertAll(
                () -> assertNotNull(emotionalLogBook.getId()),
                () -> assertNull(emotionalLogBook.getLogBookId()),
                () -> assertNull(emotionalLogBook.getDescription()),
                () -> assertNull(emotionalLogBook.getTimeType())
        );
    }

    /**
     * Проверяет конструктор с параметрами null
     */
    @ParameterizedTest(name = "[logBookId: {0}, timeType: {1}, description: {2}")
    @MethodSource("provideInvalidParams")
    public void testConstructorWithMissingRequiredValues(Long logBookId, LocalDateTime timeType, String description) {
        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(logBookId, timeType, description),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");
    }
}
