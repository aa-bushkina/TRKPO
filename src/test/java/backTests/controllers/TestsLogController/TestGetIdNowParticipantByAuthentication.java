package backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetIdNowParticipantByAuthentication {
    @Mock
    private ParticipantService participantService;

    @Mock
    private LoginInfoService loginInfoService;
    @InjectMocks
    private LogController logController;

    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testGetIdNowParticipantByAuthentication_Success() {
        Long loginInfoId = 123L;
        Long participantId = 456L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        Participant participant = new Participant();
        participant.setId(participantId);

        when(loginInfoService.findByLogin(any())).thenReturn(loginInfo);
        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        Long result = logController.getIdNowParticipantByAuthentication();

        assertEquals(participantId, result);
        verify(loginInfoService, times(1)).findByLogin(any());
        verify(participantService, times(1)).getParticipantByLoginInfoId(loginInfoId);
    }
}

