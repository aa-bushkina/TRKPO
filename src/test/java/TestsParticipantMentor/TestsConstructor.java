package TestsParticipantMentor;

import com.cygans.database.participant_mentor.ParticipantMentor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestsConstructor {

    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 2L;

    /**
     * Проверяет работу конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        ParticipantMentor participantMentor = new ParticipantMentor(PARTICIPANT_ID, MENTOR_ID);
        assertAll(
                () -> assertEquals(PARTICIPANT_ID, participantMentor.getParticipantId(), "ParticipantId не совпадает"),
                () -> assertEquals(MENTOR_ID, participantMentor.getMentorId(), "MentorId не совпадает")
        );
    }

    /**
     * Проверяет работу конструктора без параметров
     */
    @Test
    public void testConstructorWithoutParameters() {
        ParticipantMentor participantMentor = new ParticipantMentor();
        assertAll(
                () -> assertNull(participantMentor.getParticipantId(), "ParticipantId не пустой"),
                () -> assertNull(participantMentor.getMentorId(), "MentorId не пустой")
        );
    }

    /**
     * Проверяет работу конструктора с параметрами c participantId null
     */
    @Test
    public void testConstructorWithParticipantIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new ParticipantMentor(null, MENTOR_ID),
                "Не получили ожидаеме исключение при вызове метода с participantId null");
    }

    /**
     * Проверяет работу конструктора с параметрами c mentorId null
     */
    @Test
    public void testConstructorWithMentorIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new ParticipantMentor(PARTICIPANT_ID, null),
                "Не получили ожидаеме исключение при вызове метода с mentorId null");
    }

}
