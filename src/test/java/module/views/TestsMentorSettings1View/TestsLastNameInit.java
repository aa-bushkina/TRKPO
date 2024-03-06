package module.views.TestsMentorSettings1View;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.vaadin.flow.component.textfield.TextField;
import module.backTests.TestsParticipant.TestsParticipantModel.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsLastNameInit {

    @Mock
    private SettingsController settingsController;

    private MentorSettings1View mentorSettings1View;

    @BeforeEach
    void before() {
        Mentor mentor = new Mentor(Variables.FIRST_NAME, Variables.LAST_NAME, Variables.LOGIN, Variables.PHONE, Variables.GENDER, Variables.BIRTHDAY, Variables.LOGIN_INFO_ID);
        when(settingsController.getAuthoritiesMentor()).thenReturn(mentor);
        mentorSettings1View = new MentorSettings1View(settingsController);
    }

    /**
     * Проверяем работу метода lastNameInit
     */
    @Test
    void test_lastNameInit() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("lastNameInit");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field lastnameField = c.getDeclaredField("lastnameField");
        lastnameField.setAccessible(true);
        assertEquals("Фамилия", ((TextField) lastnameField.get(mentorSettings1View)).getLabel());
        assertEquals(255, ((TextField) lastnameField.get(mentorSettings1View)).getMaxLength());
        assertTrue(((TextField) lastnameField.get(mentorSettings1View)).isClearButtonVisible());
        assertTrue(((TextField) lastnameField.get(mentorSettings1View)).isReadOnly());
    }

}