package TestQuestion;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static TestQuestion.Variables.*;
import static TestQuestion.Variables.DATE_UPDATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetQuestion
{
  @Test
  public void testSetQuestion() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    question.setQuestion(QUESTION_UPDATED);
    assertEquals(QUESTION_UPDATED, question.getQuestion(), "getQuestion() вернул неверный результат");
  }
}
