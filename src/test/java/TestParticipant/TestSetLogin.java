package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetLogin {
  @Test
  public void testSetLogin() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setLogin(LOGIN_UPDATED);
    assertEquals(LOGIN_UPDATED, participant.getLogin(), "getLogin() вернул неверный результат");
  }
}
