package backTests.TestsNotifications.TetsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class TestsReplyMentorToParticipantNotification {

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
    private NotificationController notificationController;

    /**
     * Тестирование сценария, когда ответ ментора отправляется участнику и запрос разрешается.
     */
    @Test
    public void testReplyMentorToParticipantNotification() {
        Notifications thisNotification = new Notifications();
        thisNotification.setId(123L);
        String replyMsg = "Test reply message";
        notificationController.replyMentorToParticipantNotification(thisNotification, replyMsg);
        verify(notificationsService, times(1)).reply(thisNotification.getNotificationId(), replyMsg);
        verify(notificationsService, times(1)).resolveRequest(thisNotification.getNotificationId());
        verifyNoMoreInteractions(notificationsService);
    }

}
