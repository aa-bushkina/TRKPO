package TestEmotionalLogBook;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstructor {

    private static final Long LOG_BOOK_ID = 5L;
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final String DESCRIPTION = "Emotional text.";

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
    @Test
    public void testConstructorWithNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(null, null, null),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

    /**
     * Проверяет конструктор с параметром LogBookId = null
     */
    @Test
    public void testConstructorWithNullLogBookId() {
        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(null, LOCAL_DATE_TIME, DESCRIPTION),
                "Не получили ожидаеме исключение при вызове метода с параметром LogBookId = null");
    }

    /**
     * Проверяет конструктор с параметром Description = null
     */
    @Test
    public void testConstructorWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(LOG_BOOK_ID, LOCAL_DATE_TIME, null),
                "Не получили ожидаеме исключение при вызове метода с параметром Description = null");
    }

    /**
     * Проверяет конструктор с параметром TimeType = null
     */
    @Test
    public void testConstructorWithNullTimeType() {
        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(LOG_BOOK_ID, null, DESCRIPTION),
                "Не получили ожидаеме исключение при вызове метода с параметром TimeType = null");
    }

    /**
     * Проверяет, что конструктор обрабатывает случай с недостаточным количеством значений
     */
    @Test
    public void testConstructorWithMissingRequiredValues() {
        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(null, null, DESCRIPTION),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");

        assertThrows(IllegalArgumentException.class, () -> new EmotionalLogBook(LOG_BOOK_ID, null, null),
                "Не получили ожидаеме исключение при вызове метода с пустыми обязательными параметрами");
    }


}
