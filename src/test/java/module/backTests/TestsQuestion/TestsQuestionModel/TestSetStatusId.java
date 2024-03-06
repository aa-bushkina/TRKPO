package module.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.TestsQuestionModel.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetStatusId {

  @Test
  public void testSetStatusId() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    question.setStatusId(STATUS_ID_UPDATED);
    assertEquals(STATUS_ID_UPDATED, question.getStatusId(), "getStatusId() вернул неверный результат");
  }
}
