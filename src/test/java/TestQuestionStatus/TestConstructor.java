package TestQuestionStatus;

import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.QuestionStatus;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;

import static TestQuestion.Variables.*;
import static TestQuestion.Variables.STATUS_ID;
import static org.junit.jupiter.api.Assertions.*;

public class TestConstructor {

  private static final String STATUS = "status";

  @Test
  public void testConstructorWithoutParameters() {
    QuestionStatus questionStatus = new QuestionStatus();
    assertAll(
      () -> assertNull(questionStatus.getId()),
      () -> assertNull(questionStatus.getStatus())
    );
  }
  @Test
  public void testConstructorWithParameters() {
    QuestionStatus questionStatus = new QuestionStatus(STATUS);
    assertAll(
      () -> assertEquals(STATUS, questionStatus.getStatus())
    );
  }

  /**
   * Проверяет конструктор с параметрами null
   */
  @Test
  public void testConstructorWithNullValues() {
    assertThrows(IllegalArgumentException.class, () -> new QuestionStatus(null),
      "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
  }

}
