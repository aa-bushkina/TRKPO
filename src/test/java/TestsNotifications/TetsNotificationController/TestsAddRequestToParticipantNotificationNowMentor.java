package TestsNotifications.TetsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsAddRequestToParticipantNotificationNowMentor {

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
     * Тест проверяет, что метод addRequestToParticipantNotificationNowMentor правильно создает и сохраняет уведомление.
     */
    @Test
    public void testAddRequestToParticipantNotificationNowMentor() {
        String mentorLogin = "mentor123";
        Long mentorId = 1L;
        Mentor mentor = new Mentor();
        mentor.setId(mentorId);
        mentor.setFirstName("Иван");
        mentor.setLastName("Иванов");
        Participant participant = new Participant();
        participant.setId(2L);
        when(loginInfoService.findByLogin(mentorLogin)).thenReturn(new LoginInfo("", "", mentorId, (byte) 1));
        when(mentorService.getMentorById(mentorId)).thenReturn(mentor);
        when(notificationTypeService.getNotificationTypeId(TypeOfNotification.ADD_REQUEST)).thenReturn(3L);
        when(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)).thenReturn(4L);
        controller.addRequestToParticipantNotificationNowMentor(participant);
        verify(notificationsService, times(1)).saveNotification(any(Notifications.class));
    }

}
