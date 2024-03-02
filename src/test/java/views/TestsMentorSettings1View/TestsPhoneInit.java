package views.TestsMentorSettings1View;

import backTests.TestsParticipant.TestsParticipantModel.Variables;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.textfield.TextField;
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
public class TestsPhoneInit {

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
     * Проверяем работу метода phoneInit
     */
    @Test
    void test_phoneInit() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("phoneInit");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field phoneField = c.getDeclaredField("phoneField");
        phoneField.setAccessible(true);
        assertEquals("Номер телефона", ((TextField) phoneField.get(mentorSettings1View)).getLabel());
        assertEquals(12, ((TextField) phoneField.get(mentorSettings1View)).getMaxLength());
        assertEquals("+70000000000", ((TextField) phoneField.get(mentorSettings1View)).getPlaceholder());
        assertEquals("\\+7\\d{10}", ((TextField) phoneField.get(mentorSettings1View)).getPattern());
        assertTrue(((TextField) phoneField.get(mentorSettings1View)).isClearButtonVisible());
        assertTrue(((TextField) phoneField.get(mentorSettings1View)).isReadOnly());
    }

}