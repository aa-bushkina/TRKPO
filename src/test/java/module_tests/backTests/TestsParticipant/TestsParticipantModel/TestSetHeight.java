package module_tests.backTests.TestsParticipant.TestsParticipantModel;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.BIRTHDAY;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.BREAST;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.FIRST_NAME;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.GENDER;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.HEIGHT;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.HEIGHT_UPDATED;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.HIPS;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.LAST_NAME;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.LOGIN;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.LOGIN_INFO_ID;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.PHONE;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.WAIST;
import static module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables.WEIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetHeight {
    @Test
    public void testSetHeight() {
        Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
                WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
        participant.setHeight(HEIGHT_UPDATED);
        assertEquals(HEIGHT_UPDATED, participant.getHeight(), "getHeight() вернул неверный результат");
    }
}
