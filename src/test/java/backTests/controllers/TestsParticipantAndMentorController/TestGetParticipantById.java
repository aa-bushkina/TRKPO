package backTests.controllers.TestsParticipantAndMentorController;

import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGetParticipantById {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantService participantService;


    @Test
    void testGetParticipantById_WhenParticipantExists() {
        Participant expectedParticipant = new Participant();
        expectedParticipant.setId(123L);

        when(participantService.getParticipantById(expectedParticipant.getId())).thenReturn(expectedParticipant);

        Participant result = controller.getParticipantById(expectedParticipant.getId());

        assertNotNull(result);
        assertEquals(expectedParticipant, result);

        verify(participantService).getParticipantById(expectedParticipant.getId());
    }

    @Test
    void testGetParticipantById_WhenParticipantDoesNotExist() {
        Long id = 123L;

        when(participantService.getParticipantById(id)).thenReturn(null);

        Participant result = controller.getParticipantById(id);

        assertNull(result);

        verify(participantService).getParticipantById(id);
    }
}
