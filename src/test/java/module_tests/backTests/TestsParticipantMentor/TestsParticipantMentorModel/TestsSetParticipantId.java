package module_tests.backTests.TestsParticipantMentor.TestsParticipantMentorModel;

import com.cygans.database.participant_mentor.ParticipantMentor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetParticipantId {

    private static final Long PARTICIPANT_ID_BEFORE = 2L;
    private static final Long PARTICIPANT_ID_AFTER = 10L;

    /**
     * Проверяет работу setParticipantId
     */
    @Test
    public void testSetQuestionId() {
        ParticipantMentor participantMentor = new ParticipantMentor(PARTICIPANT_ID_BEFORE, 2L);
        assertEquals(PARTICIPANT_ID_BEFORE, participantMentor.getParticipantId(), "Изначально неверное значение");
        participantMentor.setParticipantId(PARTICIPANT_ID_AFTER);
        assertEquals(PARTICIPANT_ID_AFTER, participantMentor.getParticipantId(), "Установилось неверное значение");
    }

}
