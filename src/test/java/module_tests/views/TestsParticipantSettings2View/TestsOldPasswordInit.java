package module_tests.views.TestsParticipantSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.participant.settings.ParticipantSettings2View;
import com.vaadin.flow.component.textfield.PasswordField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsOldPasswordInit {


    @Mock
    private SettingsController settingsController;

    private ParticipantSettings2View participantSettings2View;

    @BeforeEach
    void before() {
        participantSettings2View = new ParticipantSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода oldPasswordInit
     */
    @Test
    void test_oldPasswordInit() throws Exception {

        Class c = ParticipantSettings2View.class;
        Method method = c.getDeclaredMethod("oldPasswordInit");
        method.setAccessible(true);
        method.invoke(participantSettings2View);

        Field oldPassword = c.getDeclaredField("oldPassword");
        oldPassword.setAccessible(true);
        assertEquals("Текущий пароль", ((PasswordField) oldPassword.get(participantSettings2View)).getLabel());
        assertTrue(((PasswordField) oldPassword.get(participantSettings2View)).isClearButtonVisible());
    }


}
