package TestsParticipantMentor;

import com.cygans.database.participant_mentor.ParticipantMentor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetParticipantId {

    private static final Long PARTICIPANT_ID = 1L;

    /**
     * Проверяет работу getParticipantId
     */
    @Test
    public void testGetParticipantId() {
        ParticipantMentor participantMentor = new ParticipantMentor(PARTICIPANT_ID, 2L);
        assertEquals(PARTICIPANT_ID, participantMentor.getParticipantId(), "Возвращается неверное значение");
    }

}
