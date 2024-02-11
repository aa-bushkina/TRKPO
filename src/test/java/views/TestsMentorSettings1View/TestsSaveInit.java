package views.TestsMentorSettings1View;
import backTests.TestsParticipant.TestsParticipantModel.Variables;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsSaveInit {

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
     * Проверяем работу метода saveSetUp
     */
    @Test
    void test_saveSetUp() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("saveInit");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field save = c.getDeclaredField("save");
        save.setAccessible(true);
        assertEquals("Сохранить", ((Button) save.get(mentorSettings1View)).getText());
        assertFalse(((Button) save.get(mentorSettings1View)).isVisible());
        assertEquals("primary", ((Button) save.get(mentorSettings1View)).getThemeName());
        assertEquals("18em", ((Button) save.get(mentorSettings1View)).getElement().getStyle().get("margin-left"));
    }

}