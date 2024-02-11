package backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetIdNowMentorByAuthentication {
    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private NotificationController notificationController;

    /**
     * Тестирование сценария, когда возвращается действительный идентификатор ментора.
     */
    @Test
    public void testGetIdNowMentorByAuthentication() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "testUser",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String username = "testUser";
        long loginInfoId = 123L;
        long mentorId = 456L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        Mentor mentor = new Mentor();
        mentor.setId(mentorId);
        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(mentorService.getMentorByLoginInfoId(loginInfo.getId())).thenReturn(mentor);
        long result = notificationController.getIdNowMentorByAuthentication();
        assertEquals(mentorId, result, "Неверный айди");
        verify(loginInfoService, times(1)).findByLogin(username);
        verify(mentorService, times(1)).getMentorByLoginInfoId(loginInfoId);
    }

}
