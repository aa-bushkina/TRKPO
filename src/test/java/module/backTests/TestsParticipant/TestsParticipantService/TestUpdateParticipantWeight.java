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
class TestUpdateParticipantWeight {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testUpdateParticipantWeightWhenParticipantExists() {
        Long participantId = 1L;
        Integer newWeight = 70;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantWeight(participantId, newWeight);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertEquals(newWeight, participant.getWeight());
    }

    @Test
    void testUpdateParticipantWeightWhenParticipantDoesNotExist() {
        Long nonExistentId = 999L;
        Integer newWeight = 70;

        when(participantRepository.getParticipantById(nonExistentId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> participantService.updateParticipantWeight(nonExistentId, newWeight));
        verify(participantRepository).getParticipantById(nonExistentId);
        verifyNoMoreInteractions(participantRepository);
    }

    @Test
    void testUpdateParticipantWeightWithNullWeight() {
        Long participantId = 1L;
        Integer newWeight = null;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantWeight(participantId, newWeight);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertNull(participant.getWeight());
    }
}

