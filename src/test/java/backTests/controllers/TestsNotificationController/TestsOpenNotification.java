package backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsOpenNotification {
    @Mock
    private NotificationStatusService notificationStatusService;

    @Mock
    private VaadinSession vaadinSession;

    @InjectMocks
    private NotificationController notificationController;

    /**
     * Проверяет корректность открытия уведомления путем установки
     * и получения атрибута "NotificationID" в объекте VaadinSession.
     */
    @Test
    public void testOpenNotification() {
        Notifications notification = new Notifications();
        notification.setId(123L);
        VaadinSession.setCurrent(vaadinSession);
        when(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN)).thenReturn(1L);
        notificationController.openNotification(notification);
        verify(vaadinSession).setAttribute("NotificationID", 123L);
        assertEquals(notification.getNotificationStatusId(), 1L, "Неверно установился id");
        verify(notificationStatusService).getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN);
        verifyNoMoreInteractions(vaadinSession, notificationStatusService);
    }

}
