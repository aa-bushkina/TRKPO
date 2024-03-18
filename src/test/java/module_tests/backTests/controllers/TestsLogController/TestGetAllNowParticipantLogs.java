package module_tests.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
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
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetAllNowParticipantLogs {
    @Mock
    private LogService logService;
    @Mock
    private LoginInfoService loginInfoService;
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
    void testGetAllNowParticipantLogsByAttribute() {
        Long participantId = 0L;
        LoginInfo loginInfo = new LoginInfo();
        Participant participant = new Participant();

        List<Log> expectedLogs = new ArrayList<>();
        when(logService.findLogBooksByParticipantId(participantId)).thenReturn(expectedLogs);
        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);

        List<Log> result = logController.getAllNowParticipantLogs(false);

        assertEquals(expectedLogs, result);

        verify(logService, times(1)).findLogBooksByParticipantId(participantId);
    }
}
