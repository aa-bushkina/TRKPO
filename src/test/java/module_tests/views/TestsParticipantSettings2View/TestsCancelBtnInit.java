package module_tests.views.TestsParticipantSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.participant.settings.ParticipantSettings2View;
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
public class TestsCancelBtnInit {

    @Mock
    private SettingsController settingsController;

    private ParticipantSettings2View participantSettings2View;

    @BeforeEach
    void before() {
        participantSettings2View = new ParticipantSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода cancelBtnInit
     */
    @Test
    void test_cancelBtnInit() throws Exception {

        Class c = ParticipantSettings2View.class;
        Method method = c.getDeclaredMethod("cancelBtnInit");
        method.setAccessible(true);
        method.invoke(participantSettings2View);

        Field cancel = c.getDeclaredField("cancelButton");
        cancel.setAccessible(true);
        assertEquals("Отменить", ((Button) cancel.get(participantSettings2View)).getText());
        assertEquals("auto", ((Button) cancel.get(participantSettings2View)).getElement().getStyle().get("margin-right"));
        ((Button) cancel.get(participantSettings2View)).click();
    }
}
