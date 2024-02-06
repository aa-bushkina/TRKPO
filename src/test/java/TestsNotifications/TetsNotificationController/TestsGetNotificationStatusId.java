package TestsNotifications.TetsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationStatusId {

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
     * Тест для метода getNotificationStatusId().
     * Проверяет корректное получение идентификатора статуса уведомления.
     */
    @Test
    public void testGetNotificationStatusId() {
        StatusOfNotification fakeStatusOfNotification = StatusOfNotification.NO_ANSWER;
        when(notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)).thenReturn(1L);
        Long result = notificationController.getNotificationStatusId(fakeStatusOfNotification);
        assertEquals(1L, result);
        verify(notificationStatusService, times(1)).getNotificationStatusId(StatusOfNotification.NO_ANSWER);
    }

}
