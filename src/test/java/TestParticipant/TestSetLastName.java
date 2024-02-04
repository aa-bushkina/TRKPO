package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetLastName {
  @Test
  public void testSetLastName() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setLastName(LAST_NAME_UPDATED);
    assertEquals(LAST_NAME_UPDATED, participant.getLastName(), "getLastName() вернул неверный результат");
  }
}
