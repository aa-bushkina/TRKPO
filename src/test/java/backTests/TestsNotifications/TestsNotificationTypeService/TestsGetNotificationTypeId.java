package backTests.TestsNotifications.TestsNotificationTypeService;

import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationTypeId {

    @Mock
    private NotificationTypeRepository repository;

    @InjectMocks
    private NotificationTypeService service;

    /**
     * Тест проверяет, что метод getNotificationTypeId возвращает правильный ID
     * для заданного значения TypeOfNotification из репозитория.
     */
    @Test
    public void testGetNotificationTypeId() {
        TypeOfNotification type = TypeOfNotification.ADD_REQUEST;
        NotificationType expectedType = new NotificationType(type.getValue());
        when(repository.findNotificationTypeByType(type.getValue())).thenReturn(expectedType);
        Long result = service.getNotificationTypeId(type);
        assertEquals(expectedType.getId(), result, "Вернулся неверный id");
    }

}
