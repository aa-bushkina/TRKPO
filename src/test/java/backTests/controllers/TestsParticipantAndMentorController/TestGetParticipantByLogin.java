package backTests.controllers.TestsParticipantAndMentorController;


import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGetParticipantByLogin {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantService participantService;


    @Test
    void testGetParticipantByLoginWhenParticipantExists() {
        Participant expectedParticipant = new Participant();
        expectedParticipant.setId(123L);
        String login = "testUser";

        when(participantService.searchParticipantId(login)).thenReturn(expectedParticipant);

        Participant result = controller.getParticipantByLogin(login);

        assertNotNull(result);
        assertEquals(expectedParticipant, result);

        verify(participantService).searchParticipantId(login);
    }

    @Test
    void testGetParticipantByLoginWhenParticipantDoesNotExist() {
        String login = "nonExistingUser";

        when(participantService.searchParticipantId(login)).thenReturn(null);

        Participant result = controller.getParticipantByLogin(login);

        assertNull(result);

        verify(participantService).searchParticipantId(login);
    }
}
