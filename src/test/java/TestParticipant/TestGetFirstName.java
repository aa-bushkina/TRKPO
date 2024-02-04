package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetFirstName {

  //Проверяет возвращаемое значение getFirstName
  @Test
  public void testGetFirstName() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    assertEquals(FIRST_NAME, participant.getFirstName(), "getFirstName() вернул неверный результат");
  }
}
