package module.backTests.TestsParticipantMentor.TestsParticipantMentorModel;

import com.cygans.database.participant_mentor.ParticipantMentor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsToString {

    private static final String NAME_CLASS = "ParticipantMentor";
    private static final String PARTICIPANT_ID_FIELD = "participantId=";
    private static final String MENTOR_ID_FIELD = "mentorId=";
    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 1L;
    private static final Long PARTICIPANT_ID_NEW = 2L;
    private static final Long MENTOR_ID_NEW = 2L;
    private static final String NULL_STRING = "null";

    /**
     * Проверяет метод toString() на наличие обязательных полей
     */
    @Test
    public void testToStringWithRequiredFields() {
        ParticipantMentor participantMentor = new ParticipantMentor(PARTICIPANT_ID, MENTOR_ID);
        String toStringResult = participantMentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(NAME_CLASS), "Нет названия класса"),
                () -> assertTrue(toStringResult.contains(PARTICIPANT_ID_FIELD), "Нет participantId"),
                () -> assertTrue(toStringResult.contains(MENTOR_ID_FIELD), "Нет mentorId")
        );
    }

    /**
     * Провеяет метод toString() с валидными значениями полей
     */
    @Test
    public void testToString() {
        ParticipantMentor participantMentor = new ParticipantMentor(PARTICIPANT_ID, MENTOR_ID);
        String toStringResult = participantMentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(PARTICIPANT_ID_FIELD + PARTICIPANT_ID), "Неверное participantId"),
                () -> assertTrue(toStringResult.contains(MENTOR_ID_FIELD + MENTOR_ID), "Неверное mentorId")
        );
    }

    /**
     * Проверяет метод toString() после изменений полей объекта
     */
    @Test
    public void testToStringAfterModifications() {
        ParticipantMentor participantMentor = new ParticipantMentor(PARTICIPANT_ID, MENTOR_ID);
        String toStringResult = participantMentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(PARTICIPANT_ID_FIELD + PARTICIPANT_ID), "Изначально неверное participantId"),
                () -> assertTrue(toStringResult.contains(MENTOR_ID_FIELD + MENTOR_ID), "Изначально неверное mentorId")
        );

        participantMentor.setParticipantId(PARTICIPANT_ID_NEW);
        participantMentor.setMentorId(MENTOR_ID_NEW);

        String toStringResultUpdated = participantMentor.toString();
        assertAll(
                () -> assertTrue(toStringResultUpdated.contains(PARTICIPANT_ID_FIELD + PARTICIPANT_ID_NEW), "Изменение неверное participantId"),
                () -> assertTrue(toStringResultUpdated.contains(MENTOR_ID_FIELD + MENTOR_ID_NEW), "Изменение неверное mentorId")
        );
    }

    /**
     * Проверяет метод toString() с пустыми значениями полей
     */
    @Test
    public void testToStringWithEmptyFields() {
        ParticipantMentor participantMentor = new ParticipantMentor();

        String toStringResult = participantMentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains(PARTICIPANT_ID_FIELD + NULL_STRING), "неверное participantId"),
                () -> assertTrue(toStringResult.contains(MENTOR_ID_FIELD + NULL_STRING), "неверное mentorId")
        );
    }

}
