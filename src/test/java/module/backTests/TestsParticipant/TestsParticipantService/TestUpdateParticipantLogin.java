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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestUpdateParticipantLogin {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testUpdateParticipantLoginWhenParticipantExists() {
        Long participantId = 1L;
        String newLogin = "john_doe";

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantLogin(participantId, newLogin);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertEquals(newLogin, participant.getLogin());
    }

    @Test
    void testUpdateParticipantLoginWhenParticipantDoesNotExist() {
        Long nonExistentId = 999L;
        String newLogin = "john_doe";

        when(participantRepository.getParticipantById(nonExistentId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> participantService.updateParticipantLogin(nonExistentId, newLogin));
        verify(participantRepository).getParticipantById(nonExistentId);
        verifyNoMoreInteractions(participantRepository);
    }

    @Test
    void testUpdateParticipantLoginWithNullLogin() {
        Long participantId = 1L;
        String newLogin = null;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantLogin(participantId, newLogin);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertNull(participant.getLogin());
    }
}
