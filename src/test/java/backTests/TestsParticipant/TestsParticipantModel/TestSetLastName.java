package backTests.TestsParticipant.TestsParticipantModel;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSetLastName {
    @Test
    public void testSetLastName() {
        Participant participant = new Participant(Variables.FIRST_NAME, Variables.LAST_NAME, Variables.LOGIN, Variables.PHONE, Variables.GENDER, Variables.BIRTHDAY, Variables.HEIGHT,
                Variables.WEIGHT, Variables.HIPS, Variables.WAIST, Variables.BREAST, Variables.LOGIN_INFO_ID);
        participant.setLastName(Variables.LAST_NAME_UPDATED);
        Assertions.assertEquals(Variables.LAST_NAME_UPDATED, participant.getLastName(), "getLastName() вернул неверный результат");
    }
}
