package TestsParticipantMentor.TestsParticipantMentorService;

import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestCreate {

    @Mock
    private ParticipantMentorRepository participantMentorRepository;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantMentorService participantMentorService;

    /**
     * Тест проверяет, что метод create вызывает save в participantMentorRepository
     * с переданными participantId и mentorId.
     */
    @Test
    public void testCreate() {
        Long participantId = 1L;
        Long mentorId = 2L;
        participantMentorService.create(participantId, mentorId);
        verify(participantMentorRepository, times(1)).save(argThat(
                argument -> argument.getParticipantId().equals(participantId)
                        && argument.getMentorId().equals(mentorId)
        ));
    }


}
