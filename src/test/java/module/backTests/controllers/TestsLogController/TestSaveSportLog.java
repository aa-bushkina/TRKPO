package module.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookService;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestSaveSportLog {
    @Mock
    private SportLogBookService sportLogBookService;
    @Mock
    private LogService logService;
    @Mock
    private LoginInfoService loginInfoService;
    @Mock
    private ParticipantService participantService;
    @Mock
    private LogsTypeService logsTypeService;
    @Mock
    private IntensityService intensityService;

    @InjectMocks
    private LogController logController;

    @Test
    void testSaveSportLogSuccess() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        Long logId = 1L;
        long expectedId = 0L;
        String intensity = "High";
        String duration = "60";
        String activity = "Running";
        String comments = "Good workout";
        LoginInfo loginInfo = new LoginInfo();
        Participant participant = new Participant();

        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(logsTypeService.getLogTypeId(LogBookType.SPORT.getText())).thenReturn(0L);
        when(intensityService.getIntensityId(intensity)).thenReturn(1L);
        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);
        doNothing().when(sportLogBookService).saveSportLog(any(SportLogBook.class));
        doNothing().when(logService).saveLog(any(Log.class));
        Long result = logController.saveSportLog(intensity, duration, activity, comments);

        assertEquals(expectedId, result);
        verify(sportLogBookService).saveSportLog(any(SportLogBook.class));
    }
}
