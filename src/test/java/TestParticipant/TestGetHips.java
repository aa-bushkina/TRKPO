package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.Test;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetHips {

  @Test
  public void testGetHips() {
    Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
      WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
    assertEquals(HIPS, participant.getHips(), "getHips() вернул неверный результат");
  }
}
