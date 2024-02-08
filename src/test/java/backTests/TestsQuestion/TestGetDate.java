package backTests.TestsQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.Variables.DATE;
import static backTests.TestsQuestion.Variables.PARTICIPANT_ID;
import static backTests.TestsQuestion.Variables.QUESTION;
import static backTests.TestsQuestion.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetDate {
    @Test
    public void testGetDate() {
        Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
        assertEquals(DATE, question.getDate(), "getDate() вернул неверный результат");
    }
}
