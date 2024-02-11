package backTests.TestsParticipant.TestsParticipantService;

import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestSearchParticipantName {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testSearchParticipantNameWhenParticipantExists() {
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setFirstName("John");
        participant.setLastName("Doe");
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        String fullName = participantService.searchParticipantName(participantId);

        assertEquals("John Doe", fullName);
        verify(participantRepository, times(2)).getParticipantById(participantId);
    }

    @Test
    void testSearchParticipantNameWhenParticipantDoesNotExist() {
        Long participantId = 1L;
        when(participantRepository.getParticipantById(participantId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            participantService.searchParticipantName(participantId);
        });
        verify(participantRepository).getParticipantById(participantId);
    }
}
