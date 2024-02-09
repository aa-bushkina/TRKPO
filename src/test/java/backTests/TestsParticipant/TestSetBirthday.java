package backTests.TestsParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static backTests.TestsParticipant.Variables.BIRTHDAY;
import static backTests.TestsParticipant.Variables.BIRTHDAY_UPDATED;
import static backTests.TestsParticipant.Variables.BREAST;
import static backTests.TestsParticipant.Variables.FIRST_NAME;
import static backTests.TestsParticipant.Variables.GENDER;
import static backTests.TestsParticipant.Variables.HEIGHT;
import static backTests.TestsParticipant.Variables.HIPS;
import static backTests.TestsParticipant.Variables.LAST_NAME;
import static backTests.TestsParticipant.Variables.LOGIN;
import static backTests.TestsParticipant.Variables.LOGIN_INFO_ID;
import static backTests.TestsParticipant.Variables.PHONE;
import static backTests.TestsParticipant.Variables.WAIST;
import static backTests.TestsParticipant.Variables.WEIGHT;
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