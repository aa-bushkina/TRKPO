package module_tests.views.TestsMentorNotificationDetailsView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.views.mentor.notifications.MentorNotificationDetailsView;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @Mock
    NotificationController notificationController;

    @Mock
    QuestionController questionController;

    @Mock
    private UI ui;

    MentorNotificationDetailsView mentorNotificationDetailsView;


    /**
     * Проверка конструктора когда DECLINE_MENTOR
     */
    @Test
    void setStyles_constructor_decline() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Отказ в отслеживании");
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class cl = MentorNotificationDetailsView.class;
        Field notification1 = cl.getDeclaredField("thisNotification");
        notification1.setAccessible(true);
        assertEquals(notification1.get(mentorNotificationDetailsView), notification);
        verify(notificationController, times(1)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
    }



    /**
     * Проверка конструктора когда не DECLINE_MENTOR
     */
    @Test
    void setStyles_constructor_not_decline() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(10L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Отказ в отслеживании");
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class cl = MentorNotificationDetailsView.class;
        Field notification1 = cl.getDeclaredField("thisNotification");
        notification1.setAccessible(true);
        assertEquals(notification1.get(mentorNotificationDetailsView), notification);
        verify(notificationController, times(0)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
    }


}
