package module_tests.backTests.TestsNotifications.TestsNotificationsService;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsRepository;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatus;
import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationType;
import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetNotificationsWithAnswerNotSeenList {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;

    /**
     * Тест проверяет, что метод getNotificationsWithAnswerNotSeenList возвращает
     * список уведомлений с указанным статусом и исключает уведомления с указанным типом для участника.
     */
    @Test
    public void testGetNotificationsWithAnswerNotSeenList() {
        Long participantId = 1L;
        Long statusId = 2L; // Предположим, что статус "ANSWERED_NOT_SEEN" имеет ID = 2
        Long declineMentorTypeId = 3L; // Предположим, что тип "DECLINE_MENTOR" имеет ID = 3
        NotificationStatus notificationStatus = new NotificationStatus();
        notificationStatus.setId(statusId);
        NotificationType notificationType = new NotificationType();
        notificationType.setId(declineMentorTypeId);
        List<Notifications> mockNotifications = Arrays.asList(
                new Notifications(1L, participantId, 1L, statusId),
                new Notifications(2L, participantId, 2L, statusId),
                new Notifications(3L, participantId, declineMentorTypeId, statusId)
        );
        when(notificationsRepository.getNotificationsByParticipantId(participantId)).thenReturn(mockNotifications);
        when(notificationStatusRepository.findNotificationStatusByStatus(StatusOfNotification.ANSWERED_NOT_SEEN.getValue())).thenReturn(notificationStatus);
        when(notificationTypeRepository.findNotificationTypeByType(TypeOfNotification.DECLINE_MENTOR.getValue())).thenReturn(notificationType);
        List<Notifications> result = service.getNotificationsWithAnswerNotSeenList(participantId);
        assertEquals(2, result.size(), "Неверный размер списка");
        assertEquals(statusId, result.get(0).getNotificationStatusId());
        assertEquals(statusId, result.get(1).getNotificationStatusId());
        assertEquals(1L, result.get(0).getNotificationTypeId());
        assertEquals(2L, result.get(1).getNotificationTypeId());
    }

    /**
     * Тест проверяет, что метод getNotificationsWithAnswerNotSeenList возвращает
     * пустой список, если для участника нет уведомлений с указанным статусом.
     */
    @Test
    public void testGetNotificationsWithAnswerNotSeenListWhenNoNotifications() {
        Long participantId = 1L;
        when(notificationsRepository.getNotificationsByParticipantId(participantId)).thenReturn(List.of());
        List<Notifications> result = service.getNotificationsWithAnswerNotSeenList(participantId);
        assertEquals(0, result.size(), "Неверный размер списка");
    }

}
