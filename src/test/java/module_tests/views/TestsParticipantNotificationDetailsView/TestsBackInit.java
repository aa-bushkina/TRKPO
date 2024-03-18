package module_tests.views.TestsParticipantNotificationDetailsView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.participant.notifications.ParticipantNotificationDetailsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsBackInit {

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
     * Проверка backInit с ADD_REQUEST
     */
    @Test
    void testsBackInit() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Добавление в отслеживание");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method backInit = c.getDeclaredMethod("backInit");
        backInit.setAccessible(true);
        backInit.invoke(participantNotificationDetailsView);

        Field backBut = c.getDeclaredField("backBut");
        backBut.setAccessible(true);
        assertEquals("Отказать", ((Button) backBut.get(participantNotificationDetailsView)).getText());
    }

    /**
     * Проверка backInit с не ADD_REQUEST
     */
    @Test
    void testsBackInit_not() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Добавление");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method backInit = c.getDeclaredMethod("backInit");
        backInit.setAccessible(true);
        backInit.invoke(participantNotificationDetailsView);

        Field backBut = c.getDeclaredField("backBut");
        backBut.setAccessible(true);
        assertEquals("Назад", ((Button) backBut.get(participantNotificationDetailsView)).getText());
    }

}
