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
class TestGetParticipantByLoginInfoId {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testGetParticipantByLoginInfoIdWhenParticipantExists() {
        Long loginInfoId = 1L;
        Participant participant = new Participant();
        participant.setId(1L);
        when(participantRepository.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        Participant foundParticipant = participantService.getParticipantByLoginInfoId(loginInfoId);

        assertEquals(participant, foundParticipant);
        verify(participantRepository).getParticipantByLoginInfoId(loginInfoId);
    }

    @Test
    void testGetParticipantByLoginInfoIdWhenParticipantDoesNotExist() {
        Long nonExistentId = 999L;
        when(participantRepository.getParticipantByLoginInfoId(nonExistentId)).thenReturn(null);

        Participant foundParticipant = participantService.getParticipantByLoginInfoId(nonExistentId);

        assertNull(foundParticipant);
        verify(participantRepository).getParticipantByLoginInfoId(nonExistentId);
    }
}


