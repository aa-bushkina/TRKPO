package module.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

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
    @ParameterizedTest(name = "[participantId: {0}, date: {1}, question: {2}, statusId: {3}")
    @MethodSource("provideParams")
    public void testConstructorWithMissingRequiredValues(Long participantId,
                                                         LocalDate date,
                                                         String question,
                                                         Long statusId) {
        assertThrows(IllegalArgumentException.class, () -> new Question(participantId, date, question, statusId),
                "Не получили ожидаеме исключение при вызове метода без обязательных параметров");
    }

    private static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of(null, Variables.DATE, Variables.QUESTION, Variables.STATUS_ID),
                Arguments.of(Variables.PARTICIPANT_ID, Variables.DATE, "", null),
                Arguments.of(Variables.PARTICIPANT_ID, null, null, Variables.STATUS_ID),
                Arguments.of(Variables.PARTICIPANT_ID, null, Variables.QUESTION, null),
                Arguments.of(null, null, Variables.QUESTION, null),
                Arguments.of(null, null, "", Variables.STATUS_ID),
                Arguments.of(null, Variables.DATE, null, null)
        );
    }
}
