package TestsNotifications.TetsNotificationController;

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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsReplyParticipantToMentorRequest {

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
     * Тестирование сценария, когда участник принимает запрос на менторство и запрос разрешается.
     */
    @Test
    public void testReplyParticipantToMentorRequest() {
        // Arrange
        Notifications thisNotification = new Notifications();
        thisNotification.setId(123L);
        thisNotification.setParticipantId(456L);
        thisNotification.setMentorId(789L);
        String participantFirstName = "John";
        String participantLastName = "Doe";
        when(participantService.getFirstname(thisNotification.getParticipantId())).thenReturn(participantFirstName);
        when(participantService.getLastname(thisNotification.getParticipantId())).thenReturn(participantLastName);
        notificationController.replyParticipantToMentorRequest(thisNotification);
        verify(participantMentorService, times(1)).create(thisNotification.getParticipantId(), thisNotification.getMentorId());
        verify(notificationsService, times(1)).reply(
                thisNotification.getNotificationId(),
                participantFirstName + " " + participantLastName + " принял запрос на менторство."
        );
        verify(notificationsService, times(1)).resolveRequest(thisNotification.getNotificationId());
        verifyNoMoreInteractions(participantMentorService, notificationsService, participantService);
    }

}
