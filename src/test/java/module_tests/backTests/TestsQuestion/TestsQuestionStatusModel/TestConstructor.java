package module_tests.backTests.TestsQuestion.TestsQuestionStatusModel;

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
     * Проверяет конструктор с параметром null
     */
    @Test
    public void testConstructorWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuestionStatus(null),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

    /**
     * Проверяет конструктор с параметраом ""
     */
    @Test
    public void testConstructorWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuestionStatus(""),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

}
