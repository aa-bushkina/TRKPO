package module.backTests.TestsParticipant.TestsParticipantModel;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.BIRTHDAY;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.BREAST;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.FIRST_NAME;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.GENDER;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.HEIGHT;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.HIPS;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.LAST_NAME;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.LOGIN;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.LOGIN_INFO_ID;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.PHONE;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.WAIST;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.WEIGHT;
import static module.backTests.TestsParticipant.TestsParticipantModel.Variables.WEIGHT_UPDATED;
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