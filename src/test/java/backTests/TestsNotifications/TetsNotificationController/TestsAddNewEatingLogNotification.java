package backTests.TestsNotifications.TetsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
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

import java.time.LocalTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsAddNewEatingLogNotification {

    @Mock
    private NotificationsService notificationsService;

    @Mock
    private NotificationTypeService notificationTypeService;

    @Mock
    private NotificationStatusService notificationStatusService;

    @Mock
    private ParticipantService participantService;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private ParticipantMentorService participantMentorService;

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private NotificationController controller;

    /**
     * Тест проверяет, что метод addNewEatingLogNotification правильно создает и сохраняет уведомление.
     */
    @Test
    public void testAddNewEatingLogNotification() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long logId = 1L;
        LocalTime time = LocalTime.of(12, 30);
        String description = "Завтрак";
        String meal_type = "Breakfast";
        Long participantId = 2L;
        Long mentorId = 3L;
        Long loginInfoId = 4L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        ParticipantMentor participantMentor = new ParticipantMentor();
        participantMentor.setMentorId(mentorId);
        Participant participant = new Participant();
        participant.setId(participantId);
        when(participantService.getFirstname(participantId)).thenReturn("Иван");
        when(participantService.getLastname(participantId)).thenReturn("Иванов");
        when(notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG)).thenReturn(4L);
        when(notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)).thenReturn(5L);
        when(participantMentorService.getMentorParticipantByParticipantId(participantId)).thenReturn(participantMentor);
        when(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(loginInfo);
        when(participantService.getParticipantByLoginInfoId(loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId())).thenReturn(participant);
        controller.addNewEatingLogNotification(logId, time, description, meal_type);
        verify(notificationsService, times(1)).saveNotification(any(Notifications.class));
    }

}