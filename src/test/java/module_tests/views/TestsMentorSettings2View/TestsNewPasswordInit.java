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
public class TestsNewPasswordInit {


    @Mock
    private SettingsController settingsController;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода newPasswordInit
     */
    @Test
    void test_newPasswordInit() throws Exception {

        Class c = MentorSettings2View.class;
        Method method = c.getDeclaredMethod("newPasswordInit");
        method.setAccessible(true);
        method.invoke(mentorSettings2View);

        Field newPassword = c.getDeclaredField("newPassword");
        newPassword.setAccessible(true);
        assertEquals("Новый пароль (не менее 8 символов)", ((PasswordField) newPassword.get(mentorSettings2View)).getLabel());
        assertEquals("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d_]{8,15}", ((PasswordField) newPassword.get(mentorSettings2View)).getPattern());
        assertEquals("Пароль должен включать букву в нижнем регистре, букву в верхнем регистре, цифру. Длина пароля 8 - 15 символов. Не используйте другие специальные символы кроме _", ((PasswordField) newPassword.get(mentorSettings2View)).getErrorMessage());
        assertTrue(((PasswordField) newPassword.get(mentorSettings2View)).isClearButtonVisible());
    }


}
