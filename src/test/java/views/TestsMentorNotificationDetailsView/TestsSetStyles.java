package views.TestsMentorNotificationDetailsView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.views.mentor.notifications.MentorNotificationDetailsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsSetStyles {

    @Mock
    NotificationController notificationController;

    @Mock
    QuestionController questionController;

    MentorNotificationDetailsView mentorNotificationDetailsView;

    /**
     * Проверка общих настроек
     */
    @Test
    void setStyles_declineMentorNotification_() throws Exception {
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
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);

        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertEquals("50%", ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getStyle().get("width"));
        assertEquals("80%", ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getMinHeight());
        assertEquals("300px", ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getMaxHeight());
        assertEquals(1, ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getMinLength());
        assertEquals(1000, ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getMaxLength());

        Field msg = c.getDeclaredField("msg");
        msg.setAccessible(true);
        assertTrue(((TextArea) msg.get(mentorNotificationDetailsView)).isReadOnly());
        assertEquals(notification.getAllMessage(), ((TextArea) msg.get(mentorNotificationDetailsView)).getValue());
        assertEquals("80%", ((TextArea) msg.get(mentorNotificationDetailsView)).getMinHeight());
        assertEquals("300px", ((TextArea) msg.get(mentorNotificationDetailsView)).getMaxHeight());
        assertEquals("50%", ((TextArea) msg.get(mentorNotificationDetailsView)).getWidth());

        Field backBut = c.getDeclaredField("backBut");
        backBut.setAccessible(true);
        assertEquals("contrast", ((Button) backBut.get(mentorNotificationDetailsView)).getThemeName());

        Field sendBut = c.getDeclaredField("sendBut");
        sendBut.setAccessible(true);
        assertEquals("primary", ((Button) sendBut.get(mentorNotificationDetailsView)).getThemeName());
    }


    /**
     * Проверка установки стилей для кнопки отправки, если тип уведомления - Отказ в отслеживании и Нет ответа
     */
    @Test
    void setStyles_declineMentorNotification_decline_no_answer() throws Exception {
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
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertFalse(((TextArea) replyMsg.get(mentorNotificationDetailsView)).isVisible());
    }


    /**
     * Проверка установки стилей для кнопки отправки, если тип уведомления - Отказ в отслеживании и ответ есть
     */
    @Test
    void setStyles_decline_answer() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(3L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Отказ в отслеживании");
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class c = MentorNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertTrue(((TextArea) replyMsg.get(mentorNotificationDetailsView)).isVisible());
        assertEquals("Ответ участника", ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getLabel());
        assertEquals(notification.getReplyMessage(), ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getValue());
    }


    /**
     * Проверка установки стилей для кнопки отправки, если тип уведомления - не отказ и нет ответа
     */
    @Test
    void setStyles_nodecline_noanswer() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(0L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Принятие");
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class c = MentorNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        Field sendBut = c.getDeclaredField("sendBut");
        sendBut.setAccessible(true);
        assertTrue(((Button) sendBut.get(mentorNotificationDetailsView)).isVisible());
        assertTrue(((TextArea) replyMsg.get(mentorNotificationDetailsView)).isClearButtonVisible());
        assertEquals("Ответ", ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getLabel());
    }

    /**
     * Проверка установки стилей для кнопки отправки, если тип уведомления - не отказ и ответ есть
     */
    @Test
    void setStyles_nodecline_answer() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(0L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(3L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR)).thenReturn(1L);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Принятие");
        mentorNotificationDetailsView = new MentorNotificationDetailsView(questionController, notificationController);
        Class c = MentorNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(mentorNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertTrue(((TextArea) replyMsg.get(mentorNotificationDetailsView)).isReadOnly());
        assertEquals("Ваш ответ", ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getLabel());
        assertEquals(notification.getReplyMessage(), ((TextArea) replyMsg.get(mentorNotificationDetailsView)).getValue());
    }




}
