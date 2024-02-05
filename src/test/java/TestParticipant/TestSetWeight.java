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
import static TestParticipant.Variables.WAIST;
import static TestParticipant.Variables.WEIGHT;
import static TestParticipant.Variables.WEIGHT_UPDATED;
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
