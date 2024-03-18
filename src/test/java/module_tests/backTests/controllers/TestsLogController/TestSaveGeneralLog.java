package module_tests.backTests.controllers.TestsLogController;//package backTests.controllers.TestsLogController;
//
//
//import com.cygans.database.controllers.LogController;
//import com.cygans.database.log_book.Log;
//import com.cygans.database.log_book.LogService;
//import com.cygans.database.log_book.logs_type.LogBookType;
//import com.cygans.database.log_book.logs_type.LogsTypeService;
//import com.cygans.database.participant.Participant;
//import com.cygans.database.participant.ParticipantService;
//import com.cygans.security.db.logInfo.LoginInfo;
//import com.cygans.security.db.logInfo.LoginInfoService;
//import com.vaadin.flow.server.VaadinSession;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class TestSaveGeneralLog {
//    @Mock
//    private LogService logService;
//    @Mock
//    private ParticipantService participantService;
//
//    @Mock
//    private LoginInfoService loginInfoService;
//
//    @Mock
//    private LogsTypeService logsTypeService;
//
//    @InjectMocks
//    private LogController logController;
//
//    @Test
//    void testSaveGeneralLogSuccess() {
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                "login",
//                "password",
//                AuthorityUtils.createAuthorityList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        LocalDate date = LocalDate.now();
//
//        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
//        when(vaadinSessionMock.getAttribute("date")).thenReturn(date);
//        VaadinSession.setCurrent(vaadinSessionMock);
//
//        LogBookType type = LogBookType.SPORT;
//        long logTypeId = 1L;
//        long expectedId = 0L;
//        Participant participant = new Participant();
//        LoginInfo loginInfo = new LoginInfo();
//
//        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
//        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);
//        when(logsTypeService.getLogTypeId(type.getText())).thenReturn(logTypeId);
//        doNothing().when(logService).saveLog(any(Log.class));
//
//
//        Long result = logController.saveGeneralLog(type);
//
//        assertEquals(result, expectedId);
//        verify(logService, times(1)).saveLog(any(Log.class));
//    }
//}
