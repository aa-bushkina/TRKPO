package module.backTests.TestsQuestion.TestsQuestionModel;

import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static backTests.TestsQuestion.TestsQuestionModel.Variables.DATE;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.PARTICIPANT_ID;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.QUESTION;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.QUESTION_UPDATED;
import static backTests.TestsQuestion.TestsQuestionModel.Variables.STATUS_ID;
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
