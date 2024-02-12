package views.TestsMentorSettings1View;

import backTests.TestsParticipant.TestsParticipantModel.Variables;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsChangePasswordInit {


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
     * Проверяем работу метода changePasswordInit
     */
    @Test
    void test_changePasswordInit() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("changePasswordInit");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field changePassword = c.getDeclaredField("changePassword");
        changePassword.setAccessible(true);
        assertEquals("Изменить пароль", ((Button) changePassword.get(mentorSettings1View)).getText());
        assertEquals("primary", ((Button) changePassword.get(mentorSettings1View)).getThemeName());
        Field save = c.getDeclaredField("save");
        save.setAccessible(true);
        assertEquals("auto", ((Button) save.get(mentorSettings1View)).getElement().getStyle().get("margin-right"));
    }

}
