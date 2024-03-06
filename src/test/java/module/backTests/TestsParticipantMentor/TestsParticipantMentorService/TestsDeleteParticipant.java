package module.backTests.TestsParticipantMentor.TestsParticipantMentorService;

import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsDeleteParticipant {

    @Mock
    private ParticipantMentorRepository participantMentorRepository;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantMentorService participantMentorService;

    /**
     * Тест проверяет, что метод deleteParticipant вызывает delete в participantMentorRepository
     * с переданным id.
     */
    @Test
    public void testDeleteParticipant() {
        Long id = 1L;
        ParticipantMentor participantMentor = new ParticipantMentor();
        when(participantMentorRepository.findByParticipantId(id)).thenReturn(participantMentor);
        participantMentorService.deleteParticipant(id);
        verify(participantMentorRepository, times(1)).delete(participantMentor);
    }

}
