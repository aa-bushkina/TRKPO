package TestQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static TestQuestion.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetQuestion {
  @Test
  public void testGetQuestion() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    assertEquals(QUESTION, question.getQuestion(), "getQuestion() вернул неверный результат");
  }
}
