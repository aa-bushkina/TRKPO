package module.views.TestsMentorSettings2View;

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
public class TestsOldPasswordInit {


    @Mock
    private SettingsController settingsController;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода oldPasswordInit
     */
    @Test
    void test_oldPasswordInit() throws Exception {

        Class c = MentorSettings2View.class;
        Method method = c.getDeclaredMethod("oldPasswordInit");
        method.setAccessible(true);
        method.invoke(mentorSettings2View);

        Field oldPassword = c.getDeclaredField("oldPassword");
        oldPassword.setAccessible(true);
        assertEquals("Текущий пароль", ((PasswordField) oldPassword.get(mentorSettings2View)).getLabel());
        assertTrue(((PasswordField) oldPassword.get(mentorSettings2View)).isClearButtonVisible());
    }


}
