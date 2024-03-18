package module_tests.backTests.TestsQuestion;


import com.cygans.database.question.question_status.StatusOfQuestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestStatusOfQuestionEnum {

    @Test
    public void testStatusOfQuestionHasText() {
        for (StatusOfQuestion status : StatusOfQuestion.values()) {
            assertNotNull(status.getText(), "Текст для " + status + " не должен быть null");
        }
    }

    @Test
    public void testGetText() {
        assertEquals("Нет ответа", StatusOfQuestion.NO_ANSWER.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для NO_ANSWER");
        assertEquals("Ответ получен", StatusOfQuestion.YES_ANSWER.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для YES_ANSWER");
    }

    @Test
    public void testEnumValuesNotNull() {
        for (StatusOfQuestion status : StatusOfQuestion.values()) {
            assertNotNull(status, "Значение Enum " + status + " не должно быть null");
        }
    }

    @Test
    public void testValueOf() {
        assertEquals(StatusOfQuestion.NO_ANSWER, StatusOfQuestion.valueOf("NO_ANSWER"),
                "Результат вызова valueOf() не совпадает с ожидаемым для NO_ANSWER");
        assertEquals(StatusOfQuestion.YES_ANSWER, StatusOfQuestion.valueOf("YES_ANSWER"),
                "Результат вызова valueOf() не совпадает с ожидаемым для YES_ANSWER");
    }

    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> StatusOfQuestion.valueOf("INVALID_TYPE"));
    }
}
