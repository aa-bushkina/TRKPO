package TestQuestionStatus;

import com.cygans.database.question.question_status.QuestionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetId {
  private static final Long ID = 0L;
  @Test
  public void testGetId() {
    QuestionStatus questionStatus = new QuestionStatus();
    assertEquals(ID, questionStatus.getId(), "getId() вернул неверный результат");
  }
}
