package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetWeight {
  @Test
  public void testSetWeight() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setWeight(WEIGHT_UPDATED);
    assertEquals(WEIGHT_UPDATED, participant.getWeight(), "getWeight() вернул неверный результат");
  }
}
