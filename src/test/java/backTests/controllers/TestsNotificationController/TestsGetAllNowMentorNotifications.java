package backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetAllNowMentorNotifications {

    @Mock
    private NotificationsService notificationsService;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private NotificationController controller;


    /**
     * Тест проверяет, что метод getAllNowMentorNotifications возвращает правильный список уведомлений.
     */
    @Test
    public void testGetAllNowMentorNotifications() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long mentorId = 1L;
        Notifications notification1 = new Notifications();
        notification1.setId(1L);
        Notifications notification2 = new Notifications();
        notification2.setId(2L);
        Long loginInfoId = 4L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        Mentor mentor = new Mentor();
        mentor.setId(mentorId);
        List<Notifications> expectedNotifications = Arrays.asList(notification1, notification2);
        when(notificationsService.getMentorNotificationlist(mentorId)).thenReturn(expectedNotifications);
        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(mentorService.getMentorByLoginInfoId(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId())).thenReturn(mentor);
        List<Notifications> actualNotifications = controller.getAllNowMentorNotifications();
        assertEquals(expectedNotifications, actualNotifications, "Списки не совпадают");
        verify(notificationsService, times(1)).getMentorNotificationlist(mentorId);
    }


}
