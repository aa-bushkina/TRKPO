package backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetAnswer {

    private static final String ANSWER = "Answer text";

    @Test
    public void testGetAnswer() {
        Question question = new Question(Variables.PARTICIPANT_ID, Variables.DATE, Variables.QUESTION, Variables.STATUS_ID);
        question.setAnswer(ANSWER);
        assertEquals(ANSWER, question.getAnswer(), "getAnswer() вернул неверный результат");
    }
}
