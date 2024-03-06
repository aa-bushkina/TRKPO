package module.backTests.TestsParticipant.TestsParticipantService;

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
class TestSearchParticipantId {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testSearchParticipantIdWhenParticipantExists() {
        String participantLogin = "john_doe";
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setLogin(participantLogin);
        when(participantRepository.getParticipantByLogin(participantLogin)).thenReturn(participant);

        Participant foundParticipant = participantService.searchParticipantId(participantLogin);

        assertEquals(participant, foundParticipant);
        verify(participantRepository).getParticipantByLogin(participantLogin);
    }

    @Test
    void testSearchParticipantIdWhenParticipantDoesNotExist() {
        String participantLogin = "non_existing_user";
        when(participantRepository.getParticipantByLogin(participantLogin)).thenReturn(null);

        Participant foundParticipant = participantService.searchParticipantId(participantLogin);

        assertNull(foundParticipant);
        verify(participantRepository).getParticipantByLogin(participantLogin);
    }
}

