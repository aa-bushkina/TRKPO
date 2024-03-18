package module_tests.backTests.controllers.TestsParticipantAndMentorController;

import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetNowParticipantByAttribute {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantService participantService;

    @BeforeEach
    public void setUp() {
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);
        when(vaadinSessionMock.getAttribute("ParticipantID")).thenReturn(123L);
    }

    @Test
    void testGetNowParticipantByAttribute() {
        Participant expectedParticipant = new Participant();
        expectedParticipant.setId(123L);

        when(participantService.getParticipantByLoginInfoId(123L)).thenReturn(expectedParticipant);

        Participant actualParticipant = controller.getNowParticipantByAttribute();

        assertEquals(expectedParticipant, actualParticipant);
    }
}
