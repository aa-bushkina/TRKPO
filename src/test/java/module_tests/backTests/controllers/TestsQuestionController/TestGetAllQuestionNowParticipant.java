package module_tests.backTests.controllers.TestsQuestionController;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetAllQuestionNowParticipant {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private ParticipantService participantService;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("testUser", "testPassword");
        SecurityContextHolder.setContext(new SecurityContextImpl());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testGetIdNowParticipantByAuthentication() {
        String username = "testUser";
        Long loginInfoId = 1L;
        Long participantId = 100L;

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        when(loginInfoService.findByLogin(username)).thenReturn(loginInfo);

        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        Long actualParticipantId = questionController.getIdNowParticipantByAuthentication();

        verify(loginInfoService).findByLogin(username);
        verify(participantService).getParticipantByLoginInfoId(loginInfoId);

        assertEquals(participantId, actualParticipantId);
    }
}
