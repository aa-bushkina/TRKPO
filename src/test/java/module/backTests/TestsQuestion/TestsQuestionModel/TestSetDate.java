package module.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.TestsQuestionModel.Variables.DATE;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.DATE_UPDATED;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.PARTICIPANT_ID;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.QUESTION;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetDate {

  @Test
  public void testSetDate() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    question.setDate(DATE_UPDATED);
    assertEquals(DATE_UPDATED, question.getDate(), "getDate() вернул неверный результат");
  }
}
