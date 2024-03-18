package module_tests.backTests.controllers.TestsParticipantAndMentorController;


import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestParticipantHaveMentor {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantMentorService participantMentorService;

    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);
    }

    @Test
    void testParticipantHaveMentor() {
        Participant participant = new Participant();
        participant.setId(123L);

        when(participantMentorService.existByParticipantId(participant.getId())).thenReturn(true);

        boolean result = controller.participantHaveMentor(participant);

        assertTrue(result);

        verify(participantMentorService).existByParticipantId(participant.getId());
    }

    @Test
    void testParticipantDoesNotHaveMentor() {
        Participant participant = new Participant();
        participant.setId(123L);

        when(participantMentorService.existByParticipantId(participant.getId())).thenReturn(false);

        boolean result = controller.participantHaveMentor(participant);

        assertFalse(result);

        verify(participantMentorService).existByParticipantId(participant.getId());
    }
}
