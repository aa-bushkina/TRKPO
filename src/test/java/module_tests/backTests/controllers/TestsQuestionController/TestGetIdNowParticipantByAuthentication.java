package module_tests.backTests.controllers.TestsQuestionController;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetIdNowParticipantByAuthentication {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private ParticipantService participantService;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private SecurityContext securityContext;

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
    void getIdNowParticipantByAuthenticationSuccess() {
        String username = "login";
        Long loginInfoId = 123L;
        Long participantId = 456L;

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        when(loginInfoService.findByLogin(username)).thenReturn(loginInfo);

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        Long result = questionController.getIdNowParticipantByAuthentication();

        assertEquals(participantId, result);
    }

    @Test
    void getIdNowParticipantByAuthenticationNoParticipantFound() {
        String username = "login";
        Long loginInfoId = 123L;

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        when(loginInfoService.findByLogin(username)).thenReturn(loginInfo);

        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> questionController.getIdNowParticipantByAuthentication());
    }
}
