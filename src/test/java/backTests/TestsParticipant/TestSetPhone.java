package backTests.TestsParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetPhone {
    @Test
    public void testSetPhone() {
        Participant participant = new Participant(Variables.FIRST_NAME, Variables.LAST_NAME, Variables.LOGIN, Variables.PHONE, Variables.GENDER, Variables.BIRTHDAY, Variables.HEIGHT,
                Variables.WEIGHT, Variables.HIPS, Variables.WAIST, Variables.BREAST, Variables.LOGIN_INFO_ID);
        participant.setPhone(Variables.PHONE_UPDATED);
        Assertions.assertEquals(Variables.PHONE_UPDATED, participant.getPhone(), "getPhone() вернул неверный результат");
    }
}
