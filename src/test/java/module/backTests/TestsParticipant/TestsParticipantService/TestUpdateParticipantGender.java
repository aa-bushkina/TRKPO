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
class TestUpdateParticipantGender {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testUpdateParticipantGenderWhenParticipantExists() {
        Long participantId = 1L;
        String newGender = "Male";

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantGender(participantId, newGender);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertEquals(newGender, participant.getGender());
    }

    @Test
    void testUpdateParticipantGenderWhenParticipantDoesNotExist() {
        Long nonExistentId = 999L;
        String newGender = "Female";

        when(participantRepository.getParticipantById(nonExistentId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> participantService.updateParticipantGender(nonExistentId, newGender));
        verify(participantRepository).getParticipantById(nonExistentId);
        verifyNoMoreInteractions(participantRepository);
    }

    @Test
    void testUpdateParticipantGenderWithNullGender() {
        Long participantId = 1L;
        String newGender = null;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantGender(participantId, newGender);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertNull(participant.getGender());
    }
}

