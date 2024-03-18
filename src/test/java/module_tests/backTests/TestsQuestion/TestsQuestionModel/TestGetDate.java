package module_tests.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static module_tests.backTests.TestsQuestion.TestsQuestionModel.Variables.DATE;
import static module_tests.backTests.TestsQuestion.TestsQuestionModel.Variables.PARTICIPANT_ID;
import static module_tests.backTests.TestsQuestion.TestsQuestionModel.Variables.QUESTION;
import static module_tests.backTests.TestsQuestion.TestsQuestionModel.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetDate {
  @Test
  public void testGetDate() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    assertEquals(DATE, question.getDate(), "getDate() вернул неверный результат");
  }
}
