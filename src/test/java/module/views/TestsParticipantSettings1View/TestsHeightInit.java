package module.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.textfield.TextField;
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
public class TestsHeightInit {

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
     * Проверяем работу метода heightInit
     */
    @Test
    void test_heightInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("heightInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field heightField = c.getDeclaredField("heightField");
        heightField.setAccessible(true);
        assertEquals("Рост", ((TextField) heightField.get(participantSettings1View)).getLabel());
        assertTrue(((TextField) heightField.get(participantSettings1View)).isClearButtonVisible());
        assertTrue(((TextField) heightField.get(participantSettings1View)).isReadOnly());
        assertEquals(3, ((TextField) heightField.get(participantSettings1View)).getMaxLength());
        assertEquals("([6][7-9]|[7-9][0-9]|[1][0-9][0-9]|[2][0-4][0-9]|[2][5][0])", ((TextField) heightField.get(participantSettings1View)).getPattern());
        assertEquals("Неверное значение роста", ((TextField) heightField.get(participantSettings1View)).getErrorMessage());
    }

}
