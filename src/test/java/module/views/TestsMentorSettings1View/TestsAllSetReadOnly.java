package module.views.TestsMentorSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import module.backTests.TestsParticipant.TestsParticipantModel.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsAllSetReadOnly {

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
     * Проверяем работу метода allSetReadOnly c true
     */
    @Test
    void test_allSetReadOnly_true() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(mentorSettings1View, true);

        Field firstnameField = c.getDeclaredField("firstnameField");
        firstnameField.setAccessible(true);
        assertTrue(((TextField) firstnameField.get(mentorSettings1View)).isReadOnly());
        Field lastnameField = c.getDeclaredField("lastnameField");
        lastnameField.setAccessible(true);
        assertTrue(((TextField) lastnameField.get(mentorSettings1View)).isReadOnly());
        Field phoneField = c.getDeclaredField("phoneField");
        phoneField.setAccessible(true);
        assertTrue(((TextField) phoneField.get(mentorSettings1View)).isReadOnly());
        Field birthSelect = c.getDeclaredField("birthSelect");
        birthSelect.setAccessible(true);
        assertTrue(((DatePicker) birthSelect.get(mentorSettings1View)).isReadOnly());
        Field genderSelect = c.getDeclaredField("genderSelect");
        genderSelect.setAccessible(true);
        assertTrue(((Select<String>) genderSelect.get(mentorSettings1View)).isReadOnly());
    }

    /**
     * Проверяем работу метода allSetReadOnly c false
     */
    @Test
    void test_allSetReadOnly_false() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(mentorSettings1View, false);

        Field firstnameField = c.getDeclaredField("firstnameField");
        firstnameField.setAccessible(true);
        assertFalse(((TextField) firstnameField.get(mentorSettings1View)).isReadOnly());
        Field lastnameField = c.getDeclaredField("lastnameField");
        lastnameField.setAccessible(true);
        assertFalse(((TextField) lastnameField.get(mentorSettings1View)).isReadOnly());
        Field phoneField = c.getDeclaredField("phoneField");
        phoneField.setAccessible(true);
        assertFalse(((TextField) phoneField.get(mentorSettings1View)).isReadOnly());
        Field birthSelect = c.getDeclaredField("birthSelect");
        birthSelect.setAccessible(true);
        assertFalse(((DatePicker) birthSelect.get(mentorSettings1View)).isReadOnly());
        Field genderSelect = c.getDeclaredField("genderSelect");
        genderSelect.setAccessible(true);
        assertFalse(((Select<String>) genderSelect.get(mentorSettings1View)).isReadOnly());
    }

}
