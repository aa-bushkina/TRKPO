package module.backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsReplyParticipantToMentorRequest {

    @Mock
    private NotificationsService notificationsService;

    @Mock
    private ParticipantService participantService;

    @Mock
    private ParticipantMentorService participantMentorService;

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
