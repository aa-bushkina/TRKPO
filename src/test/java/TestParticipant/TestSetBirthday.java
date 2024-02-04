package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetBirthday {
  @Test
  public void testSetBirthday() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    participant.setBirthday(BIRTHDAY_UPDATED);
    assertEquals(BIRTHDAY_UPDATED, participant.getBirthday(), "getBirthday() вернул неверный результат");
  }
}
