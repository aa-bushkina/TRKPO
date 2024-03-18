package module_tests.backTests.TestsNotifications.TestsNotificationTypeService;

import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationTypeType {

    @Mock
    private NotificationTypeRepository repository;

    @InjectMocks
    private NotificationTypeService service;

    /**
     * Тест проверяет, что метод getNotificationTypeType возвращает правильный тип
     * для заданного ID из репозитория.
     */
    @Test
    public void testGetNotificationTypeType() {
        Long id = 1L;
        NotificationType expectedType = new NotificationType("TestType");
        when(repository.findNotificationTypeById(id)).thenReturn(expectedType);
        String result = service.getNotificationTypeType(id);
        assertEquals(expectedType.getType(), result, "Вернулся неверный тип");
    }

}
