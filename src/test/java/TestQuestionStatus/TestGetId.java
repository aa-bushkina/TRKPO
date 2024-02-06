package TestQuestionStatus;

import com.cygans.database.question.question_status.QuestionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetId {
  @Test
  public void testGetId() {
    QuestionStatus questionStatus = new QuestionStatus();
    assertEquals(null, questionStatus.getId(), "getId() вернул неверный результат");
  }
}
