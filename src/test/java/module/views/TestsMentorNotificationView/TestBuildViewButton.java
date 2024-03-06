package module.views.TestsMentorNotificationView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.mentor.notifications.MentorNotificationView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestBuildViewButton {

    @Mock
    NotificationController notificationController;

    @Mock
    ParticipantAndMentorController participantAndMentorController;

    @Mock
    UI ui;

    @Mock
    VaadinSession vaadinSession;

    @InjectMocks
    MentorNotificationView mentorNotificationView;

    /**
     * Проверка buildParticipantViewButton
     */
    @Test
    void buildParticipantViewButton_ButtonBuilt_Successfully() {
        VaadinSession.setCurrent(vaadinSession);
        Notifications notification = new Notifications();
        notification.setId(1L);
        Button actualButton = mentorNotificationView.buildViewButton(notification);
        assertEquals("Смотреть", actualButton.getText());
        assertEquals("primary", actualButton.getThemeName());
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            actualButton.click();
        });
        actualButton.click();
    }

}