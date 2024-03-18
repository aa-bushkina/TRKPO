package module_tests.views.TestsMentorSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.views.mentor.settings.MentorSettings1View;
import module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables;
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
public class TestsInit {


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
     * Проверяем работу метода init
     */
    @Test
    void test_init() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field firstname = c.getDeclaredField("firstname");
        firstname.setAccessible(true);
        assertFalse(((String) firstname.get(mentorSettings1View)).isEmpty());

        Field lastname = c.getDeclaredField("lastname");
        lastname.setAccessible(true);
        assertFalse(((String) lastname.get(mentorSettings1View)).isEmpty());

        Field login = c.getDeclaredField("login");
        login.setAccessible(true);
        assertFalse(((String) login.get(mentorSettings1View)).isEmpty());

        Field phone = c.getDeclaredField("phone");
        phone.setAccessible(true);
        assertFalse(((String) phone.get(mentorSettings1View)).isEmpty());

        Field gender = c.getDeclaredField("gender");
        gender.setAccessible(true);
        assertFalse(((String) gender.get(mentorSettings1View)).isEmpty());

        Field birth = c.getDeclaredField("birth");
        birth.setAccessible(true);
        assertNotNull((birth.get(mentorSettings1View)));
    }

}