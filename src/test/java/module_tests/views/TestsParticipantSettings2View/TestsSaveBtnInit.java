package module_tests.views.TestsParticipantSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.participant.settings.ParticipantSettings2View;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestsSaveBtnInit {

    @Mock
    private SettingsController settingsController;

    @Mock
    private UI ui;

    private ParticipantSettings2View participantSettings2View;

    @BeforeEach
    void before() {
        participantSettings2View = new ParticipantSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода saveBtnInit
     */
    @Test
    void test_saveBtnInit() throws Exception {

        Class c = ParticipantSettings2View.class;
        Method method = c.getDeclaredMethod("saveBtnInit");
        method.setAccessible(true);
        method.invoke(participantSettings2View);

        Field confirmButton = c.getDeclaredField("confirmButton");
        confirmButton.setAccessible(true);
        assertEquals("Сохранить", ((Button) confirmButton.get(participantSettings2View)).getText());
        assertEquals("primary", ((Button) confirmButton.get(participantSettings2View)).getThemeName());
        assertEquals("1em", ((Button) confirmButton.get(participantSettings2View)).getElement().getStyle().get("margin-left"));
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) confirmButton.get(participantSettings2View)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
