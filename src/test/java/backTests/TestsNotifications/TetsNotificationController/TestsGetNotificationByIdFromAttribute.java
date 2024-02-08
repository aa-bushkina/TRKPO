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
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationByIdFromAttribute {

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

    @Mock
    private VaadinSession vaadinSession;

    @InjectMocks
    private NotificationController notificationController;


    /**
     * Тест для метода getNotificationByIdFromAttribute().
     * Проверяет корректное получение уведомления по идентификатору из атрибута сеанса Vaadin.
     */
    @Test
    public void testGetNotificationByIdFromAttribute() {
        Long fakeNotificationID = 123L;
        VaadinSession.setCurrent(vaadinSession);
        when(vaadinSession.getAttribute("NotificationID")).thenReturn(fakeNotificationID);
        Notifications fakeNotification = new Notifications();
        when(notificationsService.getNotificationById(fakeNotificationID)).thenReturn(fakeNotification);
        Notifications result = notificationController.getNotificationByIdFromAttribute();
        assertEquals(fakeNotification, result);
        verify(notificationsService, times(1)).getNotificationById(fakeNotificationID);
        verify(vaadinSession, times(1)).getAttribute("NotificationID");
    }

}
