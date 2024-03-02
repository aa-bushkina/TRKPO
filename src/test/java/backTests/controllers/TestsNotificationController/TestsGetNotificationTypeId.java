package backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
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
public class TestsGetNotificationTypeId {
    @Mock
    private NotificationTypeService notificationTypeService;

    @InjectMocks
    private NotificationController notificationController;

    /**
     * Тест для метода getNotificationTypeId().
     * Проверяет корректное получение идентификатора типа уведомления.
     */
    @Test
    public void testGetNotificationTypeId() {
        TypeOfNotification fakeTypeOfNotification = TypeOfNotification.NEW_LOG;
        when(notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG)).thenReturn(1L);
        Long result = notificationController.getNotificationTypeId(fakeTypeOfNotification);
        assertEquals(1L, result);
        verify(notificationTypeService, times(1)).getNotificationTypeId(TypeOfNotification.NEW_LOG);
    }

}
