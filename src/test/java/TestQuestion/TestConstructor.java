package TestQuestion;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.question.Question;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static TestQuestion.Variables.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstructor {

  /**
   * Проверяет конструктор с параметрами
   */
  @Test
  public void testConstructorWithParameters() {
    Question question = new Question(PARTICIPANT_ID, DATE, QUESTION, STATUS_ID);
    assertAll(
      () -> assertEquals(PARTICIPANT_ID, question.getParticipantId()),
      () -> assertEquals(DATE, question.getDate()),
      () -> assertEquals(QUESTION, question.getQuestion()),
      () -> assertEquals(STATUS_ID, question.getStatusId())
    );
  }

  /**
   * Проверяет конструктор без параметров
   */
  @Test
  public void testDefaultConstructor() {
    Question question = new Question();

    assertAll(
      () -> assertNotNull(question.getId()),
      () -> assertNull(question.getParticipantId()),
      () -> assertNull(question.getQuestion()),
      () -> assertNull(question.getStatusId()),
      () -> assertNull(question.getAnswer()),
      () -> assertNull(question.getDate())
    );
  }

  /**
   * Проверяет конструктор с параметрами null
   */
  @Test
  public void testConstructorWithNullValues() {
    assertThrows(IllegalArgumentException.class, () -> new Question(null, null, null, null),
      "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
  }

  /**
   * Проверяет, что конструктор обрабатывает случай с недостаточным количеством значений
   */
  @Test
  public void testConstructorWithMissingRequiredValues() {
    assertThrows(IllegalArgumentException.class, () -> new Question(PARTICIPANT_ID, DATE, null, null),
      "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");

    assertThrows(IllegalArgumentException.class, () -> new Question(PARTICIPANT_ID, DATE, "", null),
      "Не получили ожидаеме исключение при вызове метода с пустыми обязательными параметрами");
  }

}
