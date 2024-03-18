package module_tests.backTests.TestsParticipantMentor.TestsParticipantMentorService;

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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsIsNeedToConnectUsers {
    @Mock
    private ParticipantMentorRepository participantMentorRepository;

    @InjectMocks
    private ParticipantMentorService participantMentorService;

    /**
     * Тест проверяет, что метод isNeedToConnectUsers возвращает true,
     * когда mentorId и participantId не найдены в participantMentorRepository.
     */
    @Test
    public void testIsNeedToConnectUsersTrue() {
        Long mentId = 1L;
        Long partId = 2L;
        when(participantMentorRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(participantMentorService.isNeedToConnectUsers(mentId, partId), "Вернулся невеврный результат");
    }

    /**
     * Тест проверяет, что метод isNeedToConnectUsers возвращает false,
     * когда mentorId и participantId найдены в participantMentorRepository.
     */
    @Test
    public void testIsNeedToConnectUsersFalse() {
        Long mentId = 1L;
        Long partId = 2L;
        List<ParticipantMentor> participantMentorList = new ArrayList<>();
        participantMentorList.add(new ParticipantMentor(partId, mentId));
        when(participantMentorRepository.findAll()).thenReturn(participantMentorList);
        assertFalse(participantMentorService.isNeedToConnectUsers(mentId, partId), "Вернулся невеврный результат");
    }

    @Test
    public void testIsNeedToConnectUsersMentorIdNotMatch() {
        Long mentId = 1L;
        Long partId = 2L;

        List<ParticipantMentor> participantMentorList = new ArrayList<>();
        participantMentorList.add(new ParticipantMentor(partId, mentId + 1));
        when(participantMentorRepository.findAll()).thenReturn(participantMentorList);

        assertTrue(participantMentorService.isNeedToConnectUsers(mentId, partId), "Возвращен неверный результат");
    }

    @Test
    public void testIsNeedToConnectUsersParticipantIdNotMatch() {
        Long mentId = 1L;
        Long partId = 2L;

        List<ParticipantMentor> participantMentorList = new ArrayList<>();
        participantMentorList.add(new ParticipantMentor(partId + 1, mentId));
        when(participantMentorRepository.findAll()).thenReturn(participantMentorList);

        assertTrue(participantMentorService.isNeedToConnectUsers(mentId, partId), "Возвращен неверный результат");
    }
}
