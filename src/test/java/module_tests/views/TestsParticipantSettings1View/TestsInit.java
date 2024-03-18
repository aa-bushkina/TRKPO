package module_tests.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsInit {


    @Mock
    private SettingsController settingsController;

    private ParticipantSettings1View participantSettings1View;

    @BeforeEach
    void before() {
        Participant participant = new Participant(Variables.FIRST_NAME, Variables.LAST_NAME, Variables.LOGIN, Variables.PHONE, Variables.GENDER, Variables.BIRTHDAY, Variables.HEIGHT,
                Variables.WEIGHT, Variables.HIPS, Variables.WAIST, Variables.BREAST, Variables.LOGIN_INFO_ID);
        when(settingsController.getAuthoritiesParticipant()).thenReturn(participant);
        participantSettings1View = new ParticipantSettings1View(settingsController);
    }

    /**
     * Проверяем работу метода init
     */
    @Test
    void test_init() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field firstname = c.getDeclaredField("firstname");
        firstname.setAccessible(true);
        assertFalse(((String) firstname.get(participantSettings1View)).isEmpty());

        Field lastname = c.getDeclaredField("lastname");
        lastname.setAccessible(true);
        assertFalse(((String) lastname.get(participantSettings1View)).isEmpty());

        Field login = c.getDeclaredField("login");
        login.setAccessible(true);
        assertFalse(((String) login.get(participantSettings1View)).isEmpty());

        Field phone = c.getDeclaredField("phone");
        phone.setAccessible(true);
        assertFalse(((String) phone.get(participantSettings1View)).isEmpty());

        Field gender = c.getDeclaredField("gender");
        gender.setAccessible(true);
        assertFalse(((String) gender.get(participantSettings1View)).isEmpty());

        Field birth = c.getDeclaredField("birth");
        birth.setAccessible(true);
        assertNotNull((birth.get(participantSettings1View)));

        Field height = c.getDeclaredField("height");
        height.setAccessible(true);
        assertNotNull((height.get(participantSettings1View)));

        Field weight = c.getDeclaredField("weight");
        weight.setAccessible(true);
        assertNotNull((weight.get(participantSettings1View)));

        Field breast = c.getDeclaredField("breast");
        breast.setAccessible(true);
        assertNotNull((breast.get(participantSettings1View)));

        Field waist = c.getDeclaredField("waist");
        waist.setAccessible(true);
        assertNotNull((waist.get(participantSettings1View)));

        Field hips = c.getDeclaredField("hips");
        hips.setAccessible(true);
        assertNotNull((hips.get(participantSettings1View)));
    }

}
