package backTests.TestsNotifications.TetsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
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
public class TestsChangeTypeOrStatusNotification {

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
     * Тестирование сценария, когда изменяется тип уведомления.
     */
    @Test
    public void testChangeTypeOrStatusNotification_ChangeType() {
        Long id = 123L;
        Long typeId = 456L;
        Long statusId = null;
        notificationController.changeTypeOrStatusNotification(id, typeId, statusId);
        verify(notificationsService, times(1)).updateNotificationStatus(id, typeId);
        verifyNoMoreInteractions(notificationsService);
    }

    /**
     * Тестирование сценария, когда изменяется статус уведомления.
     */
    @Test
    public void testChangeTypeOrStatusNotification_ChangeStatus() {
        Long id = 123L;
        Long typeId = null;
        Long statusId = 789L;
        notificationController.changeTypeOrStatusNotification(id, typeId, statusId);
        verify(notificationsService, times(1)).updateNotificationStatus(id, statusId);
        verifyNoMoreInteractions(notificationsService);
    }

    /**
     * Тестирование сценария, когда ни тип, ни статус уведомления не изменяются.
     */
    @Test
    public void testChangeTypeOrStatusNotification_NoChange() {
        Long id = 123L;
        Long typeId = null;
        Long statusId = null;
        notificationController.changeTypeOrStatusNotification(id, typeId, statusId);
        verifyNoInteractions(notificationsService);
    }

}
