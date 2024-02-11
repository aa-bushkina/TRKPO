package backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationWithAnswerNotSeenParticipant {

    @Mock
    private NotificationsService notificationsService;

    @Mock
    private ParticipantService participantService;

    @Mock
    private LoginInfoService loginInfoService;

    @InjectMocks
    private NotificationController notificationController;


    /**
     * Тест, когда byAuthentication=true и getIdNowParticipantByAuthentication возвращает не null
     */
    @Test
    public void testGetNotificationWithAnswerNotSeenParticipant_Authentication_True() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long participantId = 2L;
        Long loginInfoId = 4L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        Participant participant = new Participant();
        participant.setId(participantId);
        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(participantService.getParticipantByLoginInfoId(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId())).thenReturn(participant);
        notificationController.getNotificationWithAnswerNotSeenParticipant(true, null);
        verify(notificationsService).getNotificationsWithAnswerNotSeenList(participantId);
    }

    /**
     * Тест, когда byAuthentication=true и getIdNowParticipantByAuthentication возвращает не null
     */
    @Test
    public void testGetNotificationWithAnswerNotSeenParticipant_Authentication_False() {
        Long participantId = 2L;
        Participant participant = new Participant();
        participant.setId(participantId);
        notificationController.getNotificationWithAnswerNotSeenParticipant(false, participant);
        verify(notificationsService).getNotificationsWithAnswerNotSeenList(participant.getId());
    }

}
