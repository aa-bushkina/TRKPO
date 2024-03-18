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
public class TestsFirstNameInit {

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
     * Проверяем работу метода firstNameInit
     */
    @Test
    void test_firstNameInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("firstNameInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field firstnameField = c.getDeclaredField("firstnameField");
        firstnameField.setAccessible(true);
        assertEquals("Имя", ((TextField) firstnameField.get(participantSettings1View)).getLabel());
        assertEquals(255, ((TextField) firstnameField.get(participantSettings1View)).getMaxLength());
        assertTrue(((TextField) firstnameField.get(participantSettings1View)).isClearButtonVisible());
        assertTrue(((TextField) firstnameField.get(participantSettings1View)).isReadOnly());
    }

}
