package module_tests.views.TestsParticipantNotificationDetailsView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.participant.notifications.ParticipantNotificationDetailsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsSetStyles {

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
     * Проверка общих настроек
     */
    @Test
    void testsSetStyles() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Отказ в отслеживании");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);

        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertEquals("50%", ((TextArea) replyMsg.get(participantNotificationDetailsView)).getStyle().get("width"));
        assertEquals("80%", ((TextArea) replyMsg.get(participantNotificationDetailsView)).getMinHeight());
        assertEquals("300px", ((TextArea) replyMsg.get(participantNotificationDetailsView)).getMaxHeight());
        assertEquals(1000, ((TextArea) replyMsg.get(participantNotificationDetailsView)).getMaxLength());

        Field msg = c.getDeclaredField("msg");
        msg.setAccessible(true);
        assertTrue(((TextArea) msg.get(participantNotificationDetailsView)).isReadOnly());
        assertEquals(notification.getAllMessage(), ((TextArea) msg.get(participantNotificationDetailsView)).getValue());
        assertEquals("80%", ((TextArea) msg.get(participantNotificationDetailsView)).getMinHeight());
        assertEquals("300px", ((TextArea) msg.get(participantNotificationDetailsView)).getMaxHeight());
        assertEquals("50%", ((TextArea) msg.get(participantNotificationDetailsView)).getWidth());

        Field backBut = c.getDeclaredField("backBut");
        backBut.setAccessible(true);
        assertEquals("contrast", ((Button) backBut.get(participantNotificationDetailsView)).getThemeName());

        Field sendBut = c.getDeclaredField("agreeBut");
        sendBut.setAccessible(true);
        assertEquals("primary", ((Button) sendBut.get(participantNotificationDetailsView)).getThemeName());
        assertFalse(((Button) sendBut.get(participantNotificationDetailsView)).isVisible());
    }

    /**
     * Проверка установки стилей для кнопки отправки ADD_REQUEST и ANSWERED_NOT_SEEN
     */
    @Test
    void testsSetStyles_2() throws Exception {
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
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertFalse(((TextArea) replyMsg.get(participantNotificationDetailsView)).isVisible());
        Field agreeBut = c.getDeclaredField("agreeBut");
        agreeBut.setAccessible(true);
        assertTrue(((Button) agreeBut.get(participantNotificationDetailsView)).isVisible());
    }

    /**
     * Проверка установки стилей для кнопки отправки ADD_REQUEST и не ANSWERED_NOT_SEEN
     */
    @Test
    void testsSetStyles_3() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(10L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Добавление в отслеживание");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertTrue(((TextArea) replyMsg.get(participantNotificationDetailsView)).isReadOnly());
        assertEquals("Твой ответ:", ((TextArea) replyMsg.get(participantNotificationDetailsView)).getLabel());
        assertEquals(notification.getReplyMessage(), ((TextArea) replyMsg.get(participantNotificationDetailsView)).getValue());
    }

    /**
     * Проверка установки стилей для кнопки отправки ANSWER_ON_LOG
     */
    @Test
    void testsSetStyles_4() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(10L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Ответ ментора на запись");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertFalse(((TextArea) replyMsg.get(participantNotificationDetailsView)).isVisible());
        Field agreeBut = c.getDeclaredField("agreeBut");
        agreeBut.setAccessible(true);
        assertTrue(((Button) agreeBut.get(participantNotificationDetailsView)).isVisible());
        assertEquals("Перейти к записи", ((Button) agreeBut.get(participantNotificationDetailsView)).getText());
    }

    /**
     * Проверка установки стилей для кнопки отправки ANSWER_ON_QUESTION
     */
    @Test
    void testsSetStyles_5() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(10L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Ответ ментора на вопрос");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertTrue(((TextArea) replyMsg.get(participantNotificationDetailsView)).isReadOnly());
        assertEquals("Ответ от ментора:", ((TextArea) replyMsg.get(participantNotificationDetailsView)).getLabel());
        assertEquals(notification.getReplyMessage(), ((TextArea) replyMsg.get(participantNotificationDetailsView)).getValue());
    }


    /**
     * Проверка установки стилей для кнопки отправки ANSWERED_NOT_SEEN
     */
    @Test
    void testsSetStyles_6() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(2L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Пусто");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertFalse(((TextArea) replyMsg.get(participantNotificationDetailsView)).isVisible());
    }


    /**
     * Проверка установки стилей для кнопки отправки с другими статусами и типами
     */
    @Test
    void testsSetStyles_7() throws Exception {
        Notifications notification = new Notifications();
        notification.setNotificationTypeId(1L);
        notification.setAllMessage("All");
        notification.setNotificationStatusId(200L);
        notification.setReplyMessage("Reply message");
        when(notificationController.getNotificationByIdFromAttribute()).thenReturn(notification);
        when(notificationController.getNotificationStatusId(any())).thenReturn(2L);
        when(notificationController.getTypeNotification(notification)).thenReturn("Пусто");
        participantNotificationDetailsView = new ParticipantNotificationDetailsView(notificationController, logController);
        Class c = ParticipantNotificationDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantNotificationDetailsView);
        Field replyMsg = c.getDeclaredField("replyMsg");
        replyMsg.setAccessible(true);
        assertTrue(((TextArea) replyMsg.get(participantNotificationDetailsView)).isReadOnly());
        assertEquals("Ответ от ментора:", ((TextArea) replyMsg.get(participantNotificationDetailsView)).getLabel());
        assertEquals(notification.getReplyMessage(), ((TextArea) replyMsg.get(participantNotificationDetailsView)).getValue());
    }

}
