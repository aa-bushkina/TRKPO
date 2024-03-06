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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetLastname {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testGetLastnameWhenParticipantExists() {
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setLastName("Joe");
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        String lastname = participantService.getLastname(participantId);

        assertEquals("Joe", lastname);
        verify(participantRepository).getParticipantById(participantId);
    }

    @Test
    void testGetLastnameWhenParticipantDoesNotExist() {
        Long participantId = 1L;
        when(participantRepository.getParticipantById(participantId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            participantService.getLastname(participantId);
        });
        verify(participantRepository).getParticipantById(participantId);
    }
}

