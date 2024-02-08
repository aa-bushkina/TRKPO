package backTests.TestsQuestionStatus;

import com.cygans.database.question.question_status.QuestionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstructor {

    private static final String STATUS = "status";

    @Test
    public void testConstructorWithoutParameters() {
        QuestionStatus questionStatus = new QuestionStatus();
        assertAll(
                () -> assertNull(questionStatus.getId()),
                () -> assertNull(questionStatus.getStatus())
        );
    }

    @Test
    public void testConstructorWithParameters() {
        QuestionStatus questionStatus = new QuestionStatus(STATUS);
        assertAll(
                () -> assertEquals(STATUS, questionStatus.getStatus())
        );
    }

    /**
     * Проверяет конструктор с параметрами null
     */
    @Test
    public void testConstructorWithNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new QuestionStatus(null),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

}
