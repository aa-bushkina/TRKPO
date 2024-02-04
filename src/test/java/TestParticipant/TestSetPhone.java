package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetPhone {
  @Test
  public void testSetPhone() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setPhone(PHONE_UPDATED);
    assertEquals(PHONE_UPDATED, participant.getPhone(), "getPhone() вернул неверный результат");
  }
}
