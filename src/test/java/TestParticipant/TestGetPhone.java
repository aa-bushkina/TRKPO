package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetPhone {
  @Test
  public void testGetPhone() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    assertEquals(PHONE, participant.getPhone(), "getPhone() вернул неверный результат");
  }
}
