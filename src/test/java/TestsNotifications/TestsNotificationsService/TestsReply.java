package TestsNotifications.TestsNotificationsService;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsRepository;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
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
public class TestsReply {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationStatusRepository notificationStatusRepository;

    @Mock
    private NotificationTypeRepository notificationTypeRepository;

    @InjectMocks
    private NotificationsService service;


    /**
     * Тест проверяет, что метод reply устанавливает сообщение ответа,
     * если уведомление существует и ему еще не был задан ответ.
     */
    @Test
    public void testReplyWhenNotificationExistsAndNoReplySet() {
        long notificationId = 1L;
        String replyMessage = "Ответ на уведомление";
        Notifications notification = new Notifications();
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(notification);
        service.reply(notificationId, replyMessage);
        assertEquals(replyMessage, notification.getReplyMessage(), "Неверное ответное сообщение");
        verify(notificationsRepository, times(1)).save(notification);
    }

    /**
     * Тест проверяет, что метод reply перезаписывает сообщение ответа,
     * если уведомление существует и у него уже был задан ответ.
     */
    @Test
    public void testReplyWhenNotificationExistsAndReplyAlreadySet() {
        long notificationId = 1L;
        String existingReplyMessage = "Старый ответ";
        String newReplyMessage = "Новый ответ";
        Notifications notification = new Notifications();
        notification.setReplyMessage(existingReplyMessage);
        when(notificationsRepository.getNotificationById(notificationId)).thenReturn(notification);
        service.reply(notificationId, newReplyMessage);
        assertEquals(newReplyMessage, notification.getReplyMessage(), "Неверное ответное сообщение");
        verify(notificationsRepository, times(1)).save(notification);
    }

}
