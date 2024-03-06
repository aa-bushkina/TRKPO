package module.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static module.backTests.TestsQuestion.TestsQuestionModel.Variables.DATE;
import static module.backTests.TestsQuestion.TestsQuestionModel.Variables.PARTICIPANT_ID;
import static module.backTests.TestsQuestion.TestsQuestionModel.Variables.QUESTION;
import static module.backTests.TestsQuestion.TestsQuestionModel.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetDate {
  @Test
  public void testGetDate() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    assertEquals(DATE, question.getDate(), "getDate() вернул неверный результат");
  }
}
