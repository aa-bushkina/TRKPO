package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static TestParticipant.Variables.BIRTHDAY;
import static TestParticipant.Variables.BREAST;
import static TestParticipant.Variables.FIRST_NAME;
import static TestParticipant.Variables.GENDER;
import static TestParticipant.Variables.HEIGHT;
import static TestParticipant.Variables.HIPS;
import static TestParticipant.Variables.LAST_NAME;
import static TestParticipant.Variables.LOGIN;
import static TestParticipant.Variables.LOGIN_INFO_ID;
import static TestParticipant.Variables.PHONE;
import static TestParticipant.Variables.PHONE_UPDATED;
import static TestParticipant.Variables.WAIST;
import static TestParticipant.Variables.WEIGHT;
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
