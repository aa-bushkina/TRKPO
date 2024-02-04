package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetHeight {
  @Test
  public void testSetHeight() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setHeight(HEIGHT_UPDATED);
    assertEquals(HEIGHT_UPDATED, participant.getHeight(), "getHeight() вернул неверный результат");
  }
}
