package TestQuestion;

import com.cygans.database.participant.Participant;
import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.*;
import static TestQuestion.Variables.*;
import static TestQuestion.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetDate {
  @Test
  public void testGetDate() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    assertEquals(DATE, question.getDate(), "getDate() вернул неверный результат");
  }
}
