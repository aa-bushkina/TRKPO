package backTests.TestsParticipant.TestsParticipantModel;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static backTests.TestsParticipant.TestsParticipantModel.Variables.BIRTHDAY;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.BREAST;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.FIRST_NAME;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.GENDER;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.HEIGHT;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.HIPS;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.LAST_NAME;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.LOGIN;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.LOGIN_INFO_ID;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.PHONE;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.WAIST;
import static backTests.TestsParticipant.TestsParticipantModel.Variables.WEIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetLoginInfoId {
    @Test
    public void testGetLoginInfoId() {
        Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
                WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
        assertEquals(LOGIN_INFO_ID, participant.getLoginInfoId(), "getLoginInfoId() вернул неверный результат");
    }
}