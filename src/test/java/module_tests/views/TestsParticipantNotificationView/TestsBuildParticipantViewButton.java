package module_tests.views.TestsParticipantNotificationView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.participant.notifications.ParticipantNotificationView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsBuildParticipantViewButton {

    @Mock
    NotificationController notificationController;

    @Mock
    UI ui;

    @InjectMocks
    ParticipantNotificationView participantNotificationView;

    /**
     * Проверка buildParticipantViewButton
     */
    @Test
    void buildParticipantViewButton_ButtonBuilt_Successfully() {
        Notifications notification = new Notifications();
        Button actualButton = participantNotificationView.buildParticipantViewButton(notification);
        assertEquals("Смотреть", actualButton.getText());
        assertEquals("primary", actualButton.getThemeName());
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            actualButton.click();
        });
        actualButton.click();
        verify(notificationController, times(1)).openNotification(notification);
    }

}
