package backTests.TestsParticipantMentor.TestsParticipantMentorModel;

import com.cygans.database.participant_mentor.ParticipantMentor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetMentorId {

    private static final Long MENTOR_ID = 1L;

    /**
     * Проверяет работу getMentorId
     */
    @Test
    public void testGetMentorId() {
        ParticipantMentor participantMentor = new ParticipantMentor(1L, MENTOR_ID);
        assertEquals(MENTOR_ID, participantMentor.getMentorId(), "Возвращается неверное значение");
    }

}
