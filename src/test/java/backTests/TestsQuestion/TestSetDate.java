package backTests.TestsQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.Variables.DATE;
import static backTests.TestsQuestion.Variables.DATE_UPDATED;
import static backTests.TestsQuestion.Variables.PARTICIPANT_ID;
import static backTests.TestsQuestion.Variables.QUESTION;
import static backTests.TestsQuestion.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetDate {

    @Test
    public void testSetDate() {
        Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
        question.setDate(DATE_UPDATED);
        assertEquals(DATE_UPDATED, question.getDate(), "getDate() вернул неверный результат");
    }
}
