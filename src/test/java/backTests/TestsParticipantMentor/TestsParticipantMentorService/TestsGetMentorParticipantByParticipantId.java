package backTests.TestsParticipantMentor.TestsParticipantMentorService;

import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetMentorParticipantByParticipantId {

    @Mock
    private ParticipantMentorRepository participantMentorRepository;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantMentorService participantMentorService;

    /**
     * Тест проверяет, что метод getMentorParticipantByParticipantId возвращает объект ParticipantMentor,
     * полученный из participantMentorRepository.
     */
    @Test
    public void testGetMentorParticipantByParticipantId() {
        Long participantId = 1L;
        ParticipantMentor expected = new ParticipantMentor();
        when(participantMentorRepository.findByParticipantId(participantId)).thenReturn(expected);
        ParticipantMentor result = participantMentorService.getMentorParticipantByParticipantId(participantId);
        assertEquals(expected, result, "Вернулся неверный объект");
    }

}
