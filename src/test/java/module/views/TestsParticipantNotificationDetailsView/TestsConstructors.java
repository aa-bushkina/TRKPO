package module.views.TestsParticipantNotificationDetailsView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.participant.notifications.ParticipantNotificationDetailsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
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
public class TestsConstructors {

    @Mock
    NotificationController notificationController;

    @Mock
    LogController logController;

    @Mock
    private UI ui;

    @Mock
    VaadinSession vaadinSession;

    ParticipantNotificationDetailsView participantNotificationDetailsView;

    /**
     * Проверка конструктора когда ADD_REQUEST
     */
    @Test
    void setStyles_constructor_decline() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Добавление в отслеживание");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class cl = ParticipantNotificationDetailsView.class;
        Field notification1 = cl.getDeclaredField("thisNotification");
        notification1.setAccessible(true);
        assertEquals(notification1.get(participantNotificationDetailsView), notification);
        verify(notificationController, times(0)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                2L);
    }

    /**
     * Проверка конструктора когда не ADD_REQUEST
     */
    @Test
    void setStyles_constructor() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Добавление");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class cl = ParticipantNotificationDetailsView.class;
        Field notification1 = cl.getDeclaredField("thisNotification");
        notification1.setAccessible(true);
        assertEquals(notification1.get(participantNotificationDetailsView), notification);
        verify(notificationController, times(1)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                2L);
    }


}
