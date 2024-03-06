package module.backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.NotificationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class TestsChangeTypeOrStatusNotification {

    @Mock
    private NotificationsService notificationsService;

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
