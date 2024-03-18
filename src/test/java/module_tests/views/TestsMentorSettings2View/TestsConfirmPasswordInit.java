package module_tests.views.TestsMentorSettings2View;
import com.cygans.database.controllers.SettingsController;
import com.cygans.views.mentor.settings.MentorSettings2View;
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
public class TestsConfirmPasswordInit {

    @Mock
    private SettingsController settingsController;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода confirmPasswordInit
     */
    @Test
    void test_confirmPasswordInit() throws Exception {

        Class c = MentorSettings2View.class;
        Method method = c.getDeclaredMethod("confirmPasswordInit");
        method.setAccessible(true);
        method.invoke(mentorSettings2View);

        Field confirmPassword = c.getDeclaredField("confirmPassword");
        confirmPassword.setAccessible(true);
        assertEquals("Повторите пароль", ((PasswordField) confirmPassword.get(mentorSettings2View)).getLabel());
        assertTrue(((PasswordField) confirmPassword.get(mentorSettings2View)).isClearButtonVisible());
    }


}