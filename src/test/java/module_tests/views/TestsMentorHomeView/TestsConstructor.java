package module_tests.views.TestsMentorHomeView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.mentor.Mentor;
import com.cygans.views.mentor.MentorHomeView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @Mock
    private NotificationController notificationController;

    @Mock
    private ParticipantAndMentorController participantAndMentorController;

    @Mock
    private VaadinSession vaadinSession;

    @Mock
    private Notification notification;

    @Mock
    UI ui;

    private MentorHomeView mentorHomeView;

    /**
     * Проверка конструктора когда нет ошибки
     */
    @Test
    void without_error() throws Exception {
        VaadinSession.setCurrent(vaadinSession);
        Mentor mentor = new Mentor();
        when(participantAndMentorController.getIdNowMentorByAuthentication()).thenReturn(mentor);
        when(vaadinSession.getAttribute("Error")).thenReturn(null);
        mentorHomeView = new MentorHomeView(notificationController, participantAndMentorController);
        verify(notification, times(0)).addThemeVariants(NotificationVariant.LUMO_ERROR);
        Class cl = MentorHomeView.class;
        Field mentor1 = cl.getDeclaredField("mentor");
        mentor1.setAccessible(true);
        assertEquals(mentor, (Mentor) mentor1.get(mentorHomeView), "Вернулся неверный ментор");
        Field add = cl.getDeclaredField("add");
        add.setAccessible(true);
        assertEquals("50px",((Icon) add.get(mentorHomeView)).getStyle().get("width"), "Неверный width у иконки");
        assertEquals("50px",((Icon) add.get(mentorHomeView)).getStyle().get("height"), "Неверный height у иконки");
        Field addBtn = cl.getDeclaredField("addBtn");
        addBtn.setAccessible(true);
        assertEquals("55px",((Button) addBtn.get(mentorHomeView)).getWidth(), "Width неверный у кнопки");
        assertEquals("55px",((Button) addBtn.get(mentorHomeView)).getHeight(), "Height неверный у кнопки");
        ((Button) addBtn.get(mentorHomeView)).click();
    }

    /**
     * Проверка конструктора когда есть ошибка
     */
    @Test
    void with_error() throws Exception {
        UI.setCurrent(ui);
        vaadinSession.setAttribute("Error", new Object());
        VaadinSession.setCurrent(vaadinSession);
        when(vaadinSession.getAttribute("Error")).thenReturn(new Object());
        Mentor mentor = new Mentor();
        when(participantAndMentorController.getIdNowMentorByAuthentication()).thenReturn(mentor);
        UI.getCurrent().access(() -> {
            mentorHomeView = new MentorHomeView(notificationController, participantAndMentorController);
        });
        mentorHomeView = new MentorHomeView(notificationController, participantAndMentorController);
        Class cl = MentorHomeView.class;
        Field mentor1 = cl.getDeclaredField("mentor");
        mentor1.setAccessible(true);
        assertEquals(mentor, (Mentor) mentor1.get(mentorHomeView), "Вернулся неверный ментор");
        Field add = cl.getDeclaredField("add");
        add.setAccessible(true);
        assertEquals("50px",((Icon) add.get(mentorHomeView)).getStyle().get("width"), "Неверный width у иконки");
        assertEquals("50px",((Icon) add.get(mentorHomeView)).getStyle().get("height"), "Неверный height у иконки");
        Field addBtn = cl.getDeclaredField("addBtn");
        addBtn.setAccessible(true);
        assertEquals("55px",((Button) addBtn.get(mentorHomeView)).getWidth(), "Width неверный у кнопки");
        assertEquals("55px",((Button) addBtn.get(mentorHomeView)).getHeight(), "Height неверный у кнопки");
        ((Button) addBtn.get(mentorHomeView)).click();
    }

}
