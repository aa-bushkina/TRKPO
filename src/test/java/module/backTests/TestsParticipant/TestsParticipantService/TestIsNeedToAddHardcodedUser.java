package module.backTests.TestsParticipant.TestsParticipantService;


import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestIsNeedToAddHardcodedUser {
    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testIsNeedToAddHardcodedUserWhenRepositoryIsEmpty() {
        when(participantRepository.findAll()).thenReturn(new ArrayList<>());

        boolean result = participantService.isNeedToAddHardcodedUser();

        assertTrue(result);
        verify(participantRepository).findAll();
    }

    @Test
    void testIsNeedToAddHardcodedUserWhenRepositoryIsNotEmpty() {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant());
        when(participantRepository.findAll()).thenReturn(participants);

        boolean result = participantService.isNeedToAddHardcodedUser();

        assertFalse(result);
        verify(participantRepository).findAll();
    }
}
