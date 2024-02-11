package views.TestsMentorNotificationDetailsView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.views.mentor.notifications.MentorNotificationDetailsView;
import com.cygans.views.participant.logbooks.EmotionalLogbookView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsSetNavigation {

    @Mock
    NotificationController notificationController;

    @Mock
    QuestionController questionController;

    @Mock
    private UI ui;

    MentorNotificationDetailsView mentorNotificationDetailsView;


    /**
     * Проверка setNavigation с неверным replyMsg
     */
    @Test
    void setStyles_set_navigation() throws Exception {
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
        Class c = MentorNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setNavigation");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);

        Class cl = MentorNotificationDetailsView.class;
        Field sendBut = cl.getDeclaredField("sendBut");
        sendBut.setAccessible(true);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) sendBut.get(mentorNotificationDetailsView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        Field field2 = cl.getDeclaredField("replyMsg");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("");
        field2.set(mentorNotificationDetailsView, textArea);
        ((Button) sendBut.get(mentorNotificationDetailsView)).click();
    }

    /**
     * Проверка setNavigation с вопрос нотифкацией
     */
    @Test
    void setStyles_set_navigation_question() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(2L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        notification.setQuestionId(4L);
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Отказ в отслеживании");
        when(notificationController.getNotificationTypeId(TypeOfNotification.QUESTION)).thenReturn(2L);
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class c = MentorNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setNavigation");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);

        Class cl = MentorNotificationDetailsView.class;
        Field sendBut = cl.getDeclaredField("sendBut");
        sendBut.setAccessible(true);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) sendBut.get(mentorNotificationDetailsView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        Field field2 = cl.getDeclaredField("replyMsg");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("replyMsg");
        field2.set(mentorNotificationDetailsView, textArea);
        ((Button) sendBut.get(mentorNotificationDetailsView)).click();
        verify(notificationController, times(2)).replyMentorToParticipantNotification(notification, textArea.getValue());
        verify(questionController, times(2)).addAnswerToQuestion(notification.getQuestionId(), notification.getNotificationId(), textArea.getValue());
    }

    /**
     * Проверка setNavigation с вопрос нотифкацией
     */
    @Test
    void setStyles_set_navigation_no_question() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(5L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        notification.setQuestionId(4L);
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Отказ в отслеживании");
        when(notificationController.getNotificationTypeId(TypeOfNotification.QUESTION)).thenReturn(2L);
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class c = MentorNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setNavigation");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);

        Class cl = MentorNotificationDetailsView.class;
        Field sendBut = cl.getDeclaredField("sendBut");
        sendBut.setAccessible(true);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) sendBut.get(mentorNotificationDetailsView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        Field field2 = cl.getDeclaredField("replyMsg");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("replyMsg");
        field2.set(mentorNotificationDetailsView, textArea);
        ((Button) sendBut.get(mentorNotificationDetailsView)).click();
        verify(notificationController, times(2)).replyMentorToParticipantNotification(notification, textArea.getValue());
        verify(notificationController, times(2)).changeTypeOrStatusNotification(notification.getNotificationId(),
                null,
                notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
        verify(notificationController, times(2)).addAnswerToParticipantLogNotification(notification, textArea.getValue());
    }

}
