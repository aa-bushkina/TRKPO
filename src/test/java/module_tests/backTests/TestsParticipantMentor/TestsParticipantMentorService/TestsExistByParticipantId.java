package module_tests.backTests.TestsParticipantMentor.TestsParticipantMentorService;

import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsExistByParticipantId {

    @Mock
    private ParticipantMentorRepository participantMentorRepository;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantMentorService participantMentorService;

    /**
     * Тест проверяет, что метод existByParticipantId возвращает true,
     * когда participantMentorRepository.findByParticipantId возвращает не null.
     */
    @Test
    public void testExistByParticipantIdTrue() {
        Long participantId = 1L;
        when(participantMentorRepository.findByParticipantId(participantId)).thenReturn(new ParticipantMentor());
        assertTrue(participantMentorService.existByParticipantId(participantId), "Неверный ответ");
    }

    /**
     * Тест проверяет, что метод existByParticipantId возвращает false,
     * когда participantMentorRepository.findByParticipantId возвращает null.
     */
    @Test
    public void testExistByParticipantIdFalse() {
        Long participantId = 1L;
        when(participantMentorRepository.findByParticipantId(participantId)).thenReturn(null);
        assertFalse(participantMentorService.existByParticipantId(participantId), "Неверный ответ");
    }

}
