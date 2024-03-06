package module.backTests.controllers.TestsParticipantAndMentorController;

import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetParticipantById {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantService participantService;


    @Test
    void testGetParticipantByIdWhenParticipantExists() {
        Participant expectedParticipant = new Participant();
        expectedParticipant.setId(123L);

        when(participantService.getParticipantById(expectedParticipant.getId())).thenReturn(expectedParticipant);

        Participant result = controller.getParticipantById(expectedParticipant.getId());

        assertNotNull(result);
        assertEquals(expectedParticipant, result);

        verify(participantService).getParticipantById(expectedParticipant.getId());
    }

    @Test
    void testGetParticipantByIdWhenParticipantDoesNotExist() {
        Long id = 123L;

        when(participantService.getParticipantById(id)).thenReturn(null);

        Participant result = controller.getParticipantById(id);

        assertNull(result);

        verify(participantService).getParticipantById(id);
    }
}
