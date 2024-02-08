package backTests.TestsQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstructor {

    /**
     * Проверяет конструктор с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Question question = new Question(Variables.PARTICIPANT_ID, Variables.DATE, Variables.QUESTION, Variables.STATUS_ID);
        assertAll(
                () -> Assertions.assertEquals(Variables.PARTICIPANT_ID, question.getParticipantId()),
                () -> Assertions.assertEquals(Variables.DATE, question.getDate()),
                () -> Assertions.assertEquals(Variables.QUESTION, question.getQuestion()),
                () -> Assertions.assertEquals(Variables.STATUS_ID, question.getStatusId())
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        Question question = new Question();

        assertAll(
                () -> assertNotNull(question.getId()),
                () -> assertNull(question.getParticipantId()),
                () -> assertNull(question.getQuestion()),
                () -> assertNull(question.getStatusId()),
                () -> assertNull(question.getAnswer()),
                () -> assertNull(question.getDate())
        );
    }

    /**
     * Проверяет конструктор с параметрами null
     */
    @Test
    public void testConstructorWithNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Question(null, null, null, null),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

    /**
     * Проверяет, что конструктор обрабатывает случай с недостаточным количеством значений
     */
    @Test
    public void testConstructorWithMissingRequiredValues() {
        assertThrows(IllegalArgumentException.class, () -> new Question(Variables.PARTICIPANT_ID, Variables.DATE, null, null),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");

        assertThrows(IllegalArgumentException.class, () -> new Question(Variables.PARTICIPANT_ID, Variables.DATE, "", null),
                "Не получили ожидаеме исключение при вызове метода с пустыми обязательными параметрами");
    }

}
