package module_tests.views.TestsParticipantNotificationDetailsView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.log_book.Log;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
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
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsSetNavigation {

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
     * Проверка setNavigation с ANSWER_ON_LOG
     */
    @Test
    void setStyles_set_navigation() throws Exception {
        VaadinSession.setCurrent(vaadinSession);
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(10L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        notification.setLogBookId(100L);
        Log log = new Log();
        log.setDate(LocalDate.now());
        log.setId(10L);
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST)).thenReturn(10L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Ответ ментора на запись");
        when(logController.getLogByLogbookId(notification.getLogBookId())).thenReturn(log);
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setNavigation");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);

        Class cl = ParticipantNotificationDetailsView.class;
        Field agreeBut = cl.getDeclaredField("agreeBut");
        agreeBut.setAccessible(true);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) agreeBut.get(participantNotificationDetailsView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        ((Button) agreeBut.get(participantNotificationDetailsView)).click();
        verify(notificationController, times(2)).replyParticipantToMentorRequest(notification);
        verify(notificationController, times(2)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.NO_ANSWER));
        verify(notificationController, times(3)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
        verify(logController, times(2)).getLogByLogbookId(notification.getLogBookId());
    }

    /**
     * Проверка setNavigation с не ADD_REQUEST и ANSWER_ON_LOG
     */
    @Test
    void setStyles_set_navigation_2() throws Exception {
        VaadinSession.setCurrent(vaadinSession);
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(9L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        notification.setLogBookId(100L);
        Log log = new Log();
        log.setDate(LocalDate.now());
        log.setId(10L);
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST)).thenReturn(10L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Добавление в отслеживание");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setNavigation");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);

        Class cl = ParticipantNotificationDetailsView.class;
        Field agreeBut = cl.getDeclaredField("agreeBut");
        agreeBut.setAccessible(true);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) agreeBut.get(participantNotificationDetailsView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        ((Button) agreeBut.get(participantNotificationDetailsView)).click();
        verify(notificationController, times(0)).replyParticipantToMentorRequest(notification);
        verify(notificationController, times(2)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.NO_ANSWER));
        verify(notificationController, times(2)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
        verify(logController, times(0)).getLogByLogbookId(notification.getLogBookId());
    }

}