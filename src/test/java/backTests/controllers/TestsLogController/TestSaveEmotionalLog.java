package backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
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
class TestSaveEmotionalLog {
    @Mock
    private EmotionalLogBookService emotionalLogBookService;
    @Mock
    private LoginInfoService loginInfoService;
    @Mock
    private LogsTypeService logsTypeService;
    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private LogController logController;

    @Test
    void testSaveEmotionalLogSuccess() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        Long logId = 1L;
        long expectedId = 0L;
        String comments = "Feeling good!";
        LoginInfo loginInfo = new LoginInfo();
        Participant participant = new Participant();

        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(logsTypeService.getLogTypeId(LogBookType.EMOTIONAL.getText())).thenReturn(0L);
        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);
        doNothing().when(emotionalLogBookService).saveEmotionalLog(any(EmotionalLogBook.class));

        Long result = logController.saveEmotionalLog(comments);

        assertEquals(expectedId, result);
        verify(emotionalLogBookService).saveEmotionalLog(any(EmotionalLogBook.class));
    }
}
