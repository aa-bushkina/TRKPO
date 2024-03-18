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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetFirstname {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testGetFirstnameWhenParticipantExists() {
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setFirstName("John");
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        String firstname = participantService.getFirstname(participantId);

        assertEquals("John", firstname);
        verify(participantRepository).getParticipantById(participantId);
    }

    @Test
    void testGetFirstnameWhenParticipantDoesNotExist() {
        Long participantId = 1L;
        when(participantRepository.getParticipantById(participantId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            participantService.getFirstname(participantId);
        });
        verify(participantRepository).getParticipantById(participantId);
    }
}

