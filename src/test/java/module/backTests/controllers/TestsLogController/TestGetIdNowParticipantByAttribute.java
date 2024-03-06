package module.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetIdNowParticipantByAttribute {
    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private LogController logController;

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
    void testGetIdNowParticipantByAttributeSuccess() {
        Long participantId = 123L;
        Participant participant = new Participant();
        participant.setId(participantId);
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);

        when(vaadinSessionMock.getAttribute("ParticipantID")).thenReturn(participantId);
        when(participantService.getParticipantByLoginInfoId(participantId)).thenReturn(participant);

        VaadinSession.setCurrent(vaadinSessionMock);

        Long result = logController.getIdNowParticipantByAttribute();

        assertEquals(participantId, result);
        verify(participantService, times(1)).getParticipantByLoginInfoId(participantId);
    }
}

