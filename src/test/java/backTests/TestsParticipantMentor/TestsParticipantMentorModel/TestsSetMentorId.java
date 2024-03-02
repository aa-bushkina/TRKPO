package backTests.TestsParticipantMentor.TestsParticipantMentorModel;

import com.cygans.database.participant_mentor.ParticipantMentor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetMentorId {

    private static final Long MENTOR_ID_BEFORE = 2L;
    private static final Long MENTOR_ID_AFTER = 10L;

    /**
     * Проверяет работу setMentorId
     */
    @Test
    public void testSetQuestionId() {
        ParticipantMentor participantMentor = new ParticipantMentor(MENTOR_ID_BEFORE, 2L);
        assertEquals(MENTOR_ID_BEFORE, participantMentor.getMentorId(), "Изначально неверное значение");
        participantMentor.setMentorId(MENTOR_ID_AFTER);
        assertEquals(MENTOR_ID_AFTER, participantMentor.getMentorId(), "Установилось неверное значение");
    }

}
