package backTests.TestsParticipant.TestsParticipantService;


import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
class TestUpdateParticipantHeight {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testUpdateParticipantHeightWhenParticipantExists() {
        Long participantId = 1L;
        Integer newHeight = 180;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantHeight(participantId, newHeight);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertEquals(newHeight, participant.getHeight());
    }

    @Test
    void testUpdateParticipantHeightWhenParticipantDoesNotExist() {
        Long nonExistentId = 999L;
        Integer newHeight = 180;

        when(participantRepository.getParticipantById(nonExistentId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> participantService.updateParticipantHeight(nonExistentId, newHeight));
        verify(participantRepository).getParticipantById(nonExistentId);
        verifyNoMoreInteractions(participantRepository);
    }

    @Test
    void testUpdateParticipantHeightWithNullHeight() {
        Long participantId = 1L;
        Integer newHeight = null;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantHeight(participantId, newHeight);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertNull(participant.getHeight());
    }

    @ParameterizedTest(name = "Тест неудовлетворяющих граничных значений")
    @ValueSource(ints = {66, 251})
    void testUpdateParticipantHeightBadPass(int height) {
        Long participantId = 1L;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantHeight(participantId, height);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertNull(participant.getHeight(), "Ожидалось пустое значение роста из-за неудовлетворяющего значения");
    }

    @ParameterizedTest(name = "Тест удовлетворяющих граничных значений")
    @ValueSource(ints = {67, 250})
    void testUpdateParticipantHeightGoodPass(int height) {
        Long participantId = 1L;

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantRepository.getParticipantById(participantId)).thenReturn(participant);

        participantService.updateParticipantHeight(participantId, height);

        verify(participantRepository).getParticipantById(participantId);
        verify(participantRepository).save(participant);
        assertEquals(height, participant.getHeight(), "Не совпадает установленный рост с ожидаемым");
    }
}

