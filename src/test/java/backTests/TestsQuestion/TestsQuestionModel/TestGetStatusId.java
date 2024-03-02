package backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.TestsQuestionModel.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetStatusId {
  @Test
  public void testGetStatusId() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    assertEquals(STATUS_ID, question.getStatusId(), "getParticipantId() вернул неверный результат");
  }
}
