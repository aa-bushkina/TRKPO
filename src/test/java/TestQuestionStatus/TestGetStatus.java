package TestQuestionStatus;

import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.QuestionStatus;
import org.junit.jupiter.api.Test;

import static TestQuestion.Variables.*;
import static TestQuestion.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetStatus {
  private static final String STATUS = "Status";
  @Test
  public void testGetStatus() {
    QuestionStatus questionStatus = new QuestionStatus(STATUS);
    assertEquals(STATUS, questionStatus.getStatus(), "getStatus() вернул неверный результат");
  }
}
