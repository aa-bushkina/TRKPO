package backTests.TestsQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.Variables.DATE;
import static backTests.TestsQuestion.Variables.PARTICIPANT_ID;
import static backTests.TestsQuestion.Variables.QUESTION;
import static backTests.TestsQuestion.Variables.STATUS_ID;
import static backTests.TestsQuestion.Variables.STATUS_ID_UPDATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetStatusId {

    @Test
    public void testSetStatusId() {
        Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
        question.setStatusId(STATUS_ID_UPDATED);
        assertEquals(STATUS_ID_UPDATED, question.getStatusId(), "getStatusId() вернул неверный результат");
    }
}
