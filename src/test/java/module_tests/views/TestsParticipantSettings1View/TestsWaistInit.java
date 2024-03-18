package module_tests.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.textfield.TextField;
import module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables;
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
public class TestsWaistInit {

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
     * Проверяем работу метода waistInit
     */
    @Test
    void test_waistInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("waistInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field waistField = c.getDeclaredField("waistField");
        waistField.setAccessible(true);
        assertEquals("Обхват талии", ((TextField) waistField.get(participantSettings1View)).getLabel());
        assertTrue(((TextField) waistField.get(participantSettings1View)).isClearButtonVisible());
        assertTrue(((TextField) waistField.get(participantSettings1View)).isReadOnly());
        assertEquals(3, ((TextField) waistField.get(participantSettings1View)).getMaxLength());
        assertEquals("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])", ((TextField) waistField.get(participantSettings1View)).getPattern());
        assertEquals("Неверное значение обхвата талии", ((TextField) waistField.get(participantSettings1View)).getErrorMessage());
    }

}
