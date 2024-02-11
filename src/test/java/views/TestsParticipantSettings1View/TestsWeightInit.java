package views.TestsParticipantSettings1View;

import backTests.TestsParticipant.TestsParticipantModel.Variables;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
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
public class TestsWeightInit {

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
     * Проверяем работу метода weightInit
     */
    @Test
    void test_weightInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("weightInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field weightField = c.getDeclaredField("weightField");
        weightField.setAccessible(true);
        assertEquals("Вес", ((TextField) weightField.get(participantSettings1View)).getLabel());
        assertTrue(((TextField) weightField.get(participantSettings1View)).isClearButtonVisible());
        assertTrue(((TextField) weightField.get(participantSettings1View)).isReadOnly());
        assertEquals(3, ((TextField) weightField.get(participantSettings1View)).getMaxLength());
        assertEquals("([2-9]|[1-9][0-9]|[1-5][0-9][0-9]|[6][0][0])", ((TextField) weightField.get(participantSettings1View)).getPattern());
        assertEquals("Неверное значение веса", ((TextField) weightField.get(participantSettings1View)).getErrorMessage());
    }

}
