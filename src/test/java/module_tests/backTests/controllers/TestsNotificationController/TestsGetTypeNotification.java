package module_tests.backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetTypeNotification {

    @Mock
    private NotificationTypeService notificationTypeService;

    @InjectMocks
    private NotificationController notificationController;

    /**
     * Тест для метода getTypeNotification().
     * Проверяет корректное получение типа уведомления.
     */
    @Test
    public void testGetTypeNotification() {
        Notifications fakeNotification = new Notifications();
        fakeNotification.setNotificationTypeId(1L);
        when(notificationTypeService.getNotificationTypeType(1L)).thenReturn("TestType");
        String result = notificationController.getTypeNotification(fakeNotification);
        assertEquals("TestType", result, "Вернулся неверный тип");
        verify(notificationTypeService, times(1)).getNotificationTypeType(1L);
    }

    /**
     * Тест для метода getTypeNotification().
     * Проверяет, что метод выбрасывает исключение, если передано null-значение для уведомления.
     */
    @Test
    public void testGetTypeNotification_NullNotification() {
        assertThrows(NullPointerException.class, () -> notificationController.getTypeNotification(null));
        verify(notificationTypeService, never()).getNotificationTypeType(anyLong());
    }

}
