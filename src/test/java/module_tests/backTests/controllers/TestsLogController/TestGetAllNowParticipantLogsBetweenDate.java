package module_tests.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TestGetAllNowParticipantLogsBetweenDate {
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
    void testGetAllNowParticipantLogsBetweenDate_WithParticipant() {
        LocalDate checkDate = LocalDate.of(2023, 1, 1);
        LocalDate today = LocalDate.of(2023, 12, 31);
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setId(participantId);

        List<Log> expectedLogs = new ArrayList<>();
        when(logService.findLogBooksBetweenDate(checkDate, today, participantId)).thenReturn(expectedLogs);

        List<Log> result = logController.getAllNowParticipantLogsBetweenDate(checkDate, today, false, participant);

        assertEquals(expectedLogs, result);

        verify(logService, times(1)).findLogBooksBetweenDate(checkDate, today, participantId);
    }

//    @Test
//    void testGetAllNowParticipantLogsBetweenDate_ByAuthentication() {
//        LocalDate checkDate = LocalDate.of(2023, 1, 1);
//        LocalDate today = LocalDate.of(2023, 12, 31);
//        Long participantId = 0L;
//        LoginInfo loginInfo = new LoginInfo();
//        Participant participant = new Participant();
//
//        List<Log> expectedLogs = new ArrayList<>();
//        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
//        when(logService.findLogBooksBetweenDate(checkDate, today, participantId)).thenReturn(expectedLogs);
//        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);
//
//        List<Log> result = logController.getAllNowParticipantLogsBetweenDate(checkDate, today, true, null);
//
//        assertEquals(expectedLogs, result);
//
//        verify(logService, times(1)).findLogBooksBetweenDate(checkDate, today, participantId);
//    }

    @Test
    void testGetAllNowParticipantLogsBetweenDate_ByAttribute() {
        LocalDate checkDate = LocalDate.of(2023, 1, 1);
        LocalDate today = LocalDate.of(2023, 12, 31);
        Long participantId = 0L;
        Participant participant = new Participant();

        List<Log> expectedLogs = new ArrayList<>();
        when(logService.findLogBooksBetweenDate(checkDate, today, participantId)).thenReturn(expectedLogs);
        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);

        List<Log> result = logController.getAllNowParticipantLogsBetweenDate(checkDate, today, false, null);

        assertEquals(expectedLogs, result);

        verify(logService, times(1)).findLogBooksBetweenDate(checkDate, today, participantId);
    }
}
