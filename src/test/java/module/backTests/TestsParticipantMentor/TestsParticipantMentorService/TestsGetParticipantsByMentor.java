package module.backTests.TestsParticipantMentor.TestsParticipantMentorService;

import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetParticipantsByMentor {

    @Mock
    private ParticipantMentorRepository participantMentorRepository;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantMentorService participantMentorService;

    /**
     * Тест проверяет, что метод getParticipantsByMentor возвращает список участников,
     * полученный из participantService, на основе данных из participantMentorRepository.
     */
    @Test
    public void testGetParticipantsByMentor() {
        Long mentorId = 1L;
        List<ParticipantMentor> participantMentorList = new ArrayList<>();
        participantMentorList.add(new ParticipantMentor(1L, mentorId));
        when(participantMentorRepository.getAllByMentorId(mentorId)).thenReturn(participantMentorList);
        when(participantService.getParticipantById(1L)).thenReturn(new Participant());
        List<Participant> result = participantMentorService.getParticipantsByMentor(mentorId);
        assertEquals(1, result.size(), "Неверное количество участников");
    }

}
