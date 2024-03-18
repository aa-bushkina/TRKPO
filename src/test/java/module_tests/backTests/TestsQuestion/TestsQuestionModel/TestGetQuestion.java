package module_tests.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGetQuestion {
    @Test
    public void testGetQuestion() {
        Question question = new Question(Variables.PARTICIPANT_ID, Variables.DATE, Variables.QUESTION, Variables.STATUS_ID);
        Assertions.assertEquals(Variables.QUESTION, question.getQuestion(), "getQuestion() вернул неверный результат");
    }
}