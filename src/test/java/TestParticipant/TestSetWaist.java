package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetWaist {
  @Test
  public void testSetWaist() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setWaist(WAIST_UPDATED);
    assertEquals(WAIST_UPDATED, participant.getWaist(), "getWaist() вернул неверный результат");
  }
}
