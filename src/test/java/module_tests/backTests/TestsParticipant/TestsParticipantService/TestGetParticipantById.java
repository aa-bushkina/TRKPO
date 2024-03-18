package module_tests.backTests.TestsParticipant.TestsParticipantService;

import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetParticipantById {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testGetParticipantByIdWhenParticipantExists() {
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        Participant foundParticipant = participantService.getParticipantById(participantId);

        assertEquals(participant, foundParticipant);
        verify(participantRepository).getParticipantById(participantId);
    }

    @Test
    void testGetParticipantByIdWhenParticipantDoesNotExist() {
        Long nonExistentId = 999L;
        when(participantRepository.getParticipantById(nonExistentId)).thenReturn(null);

        Participant foundParticipant = participantService.getParticipantById(nonExistentId);

        assertNull(foundParticipant);
        verify(participantRepository).getParticipantById(nonExistentId);
    }
}

