package backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookService;
import com.cygans.database.eating_log_book.meal.MealService;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
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

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestSaveEatingLog {
    @Mock
    private EatingLogBookService eatingLogBookService;
    @Mock
    private LoginInfoService loginInfoService;
    @Mock
    private ParticipantService participantService;
    @Mock
    private LogsTypeService logsTypeService;
    @Mock
    private MealService mealService;
    @Mock
    private LogService logService;

    @InjectMocks
    private LogController logController;

    @Test
    void testSaveEatingLogSuccess() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        long expectedId = 0L;
        LocalTime time = LocalTime.now();
        String description = "Breakfast";
        String mealType = "Lunch";
        LoginInfo loginInfo = new LoginInfo();
        Participant participant = new Participant();

        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(logsTypeService.getLogTypeId(LogBookType.EATING.getText())).thenReturn(0L);
        when(mealService.getMealId(mealType)).thenReturn(1L);
        when(participantService.getParticipantByLoginInfoId(null)).thenReturn(participant);
        doNothing().when(eatingLogBookService).saveEatingLog(any(EatingLogBook.class));
        doNothing().when(logService).saveLog(any(Log.class));

        Long result = logController.saveEatingLog(time, description, mealType);

        assertEquals(expectedId, result);
        verify(eatingLogBookService).saveEatingLog(any(EatingLogBook.class));
    }
}
