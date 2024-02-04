package TestParticipant;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверяет конструктор
 */
public class TestParticipantConstructor {

  /**
   * Проверяет конструктор с параметрами
   */
  @Test
  public void testConstructorWithParameters() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
                                                                WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);

    assertAll(
      () -> assertEquals(FIRST_NAME, participant.getFirstName()),
      () -> assertEquals(LAST_NAME, participant.getLastName()),
      () -> assertEquals(LOGIN, participant.getLogin()),
      () -> assertEquals(PHONE, participant.getPhone()),
      () -> assertEquals(GENDER, participant.getGender()),
      () -> assertEquals(BIRTHDAY, participant.getBirthday()),
      () -> assertEquals(HEIGHT, participant.getHeight()),
      () -> assertEquals(WEIGHT, participant.getWeight()),
      () -> assertEquals(HIPS, participant.getHips()),
      () -> assertEquals(WAIST, participant.getWaist()),
      () -> assertEquals(BREAST, participant.getBreast()),
      () -> assertEquals(LOGIN_INFO_ID, participant.getLoginInfoId())
    );
  }

  /**
   * Проверяет конструктор без параметров
   */
  @Test
  public void testDefaultConstructor() {
    Participant participant = new Participant();

    assertAll(
      () -> assertNull(participant.getFirstName()),
      () -> assertNull(participant.getLastName()),
      () -> assertNull(participant.getLogin()),
      () -> assertNull(participant.getPhone()),
      () -> assertNull(participant.getGender()),
      () -> assertNull(participant.getBirthday()),
      () -> assertNull(participant.getHeight()),
      () -> assertNull(participant.getWeight()),
      () -> assertNull(participant.getHips()),
      () -> assertNull(participant.getWaist()),
      () -> assertNull(participant.getBreast()),
      () -> assertNull(participant.getLoginInfoId())
    );
  }

  /**
   * Проверяет конструктор с параметрами null
   */
  @Test
  public void testConstructorWithNullValues() {
    assertThrows(IllegalArgumentException.class, () -> new Participant(null, null, null, null, null, null, null, null, null, null, null, null),
      "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
  }

  /**
   * Проверяет, что конструктор обрабатывает случай с недостаточным количеством значений
   */
  @Test
  public void testConstructorWithMissingRequiredValues() {
    assertThrows(IllegalArgumentException.class, () -> new Participant(FIRST_NAME, LAST_NAME, null, null, GENDER, null, null, null, null, null, null, LOGIN_INFO_ID ),
      "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");

    assertThrows(IllegalArgumentException.class, () ->  new Participant("", "", "", PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
      "Не получили ожидаеме исключение при вызове метода с пустыми обязательными параметрами");
  }
}
