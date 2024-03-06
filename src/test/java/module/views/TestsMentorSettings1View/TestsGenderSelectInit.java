package module.views.TestsMentorSettings1View;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.vaadin.flow.component.select.Select;
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
public class TestsGenderSelectInit {

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
     * Проверяем работу метода genderSelectInit
     */
    @Test
    void test_genderSelectInit() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("genderSelectInit");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field genderSelect = c.getDeclaredField("genderSelect");
        genderSelect.setAccessible(true);
        assertEquals("Пол", ((Select<Object>) genderSelect.get(mentorSettings1View)).getLabel());
        assertTrue(((Select<Object>) genderSelect.get(mentorSettings1View)).isReadOnly());
    }

}