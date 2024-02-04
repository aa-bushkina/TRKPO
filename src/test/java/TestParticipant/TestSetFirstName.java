package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetFirstName {
  @Test
  public void testSetFirstName() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setFirstName(FIRST_NAME_UPDATED);
    assertEquals(FIRST_NAME_UPDATED, participant.getFirstName(), "getFirstName() вернул неверный результат");
  }
}
