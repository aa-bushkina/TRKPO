package TestQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static TestQuestion.Variables.DATE;
import static TestQuestion.Variables.PARTICIPANT_ID;
import static TestQuestion.Variables.QUESTION;
import static TestQuestion.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstructor {

    /**
     * Проверяет конструктор с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
        assertAll(
                () -> assertEquals(PARTICIPANT_ID, question.getParticipantId()),
                () -> assertEquals(DATE, question.getDate()),
                () -> assertEquals(QUESTION, question.getQuestion()),
                () -> assertEquals(STATUS_ID, question.getStatusId())
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

    private static Stream<Arguments> provideInvalidParams() {
        return Stream.of(
                Arguments.of(null, DATE, QUESTION, STATUS_ID),
                Arguments.of(PARTICIPANT_ID, null, QUESTION, STATUS_ID),
                Arguments.of(PARTICIPANT_ID, DATE, null, STATUS_ID),
                Arguments.of(PARTICIPANT_ID, DATE, QUESTION, null),
                Arguments.of(PARTICIPANT_ID, DATE, "", STATUS_ID),
                Arguments.of(null, null, null, null)
        );
    }

    @ParameterizedTest(name = "[participantId: {0}, date: {1}, question: {2}, statusId: {4}")
    @MethodSource("provideInvalidParams")
    public void testConstructorWithMissingRequiredValues(Long participantId,
                                                         LocalDate date,
                                                         String question,
                                                         Long statusId) {
        assertThrows(IllegalArgumentException.class, () -> new Question(participantId, date, question, statusId),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");
    }

}
