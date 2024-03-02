package views.TestsParticipantSettings1View;

import backTests.TestsParticipant.TestsParticipantModel.Variables;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsLoginFieldInit {


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
     * Проверяем работу метода loginFieldInit
     */
    @Test
    void test_loginFieldInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("loginFieldInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field loginField = c.getDeclaredField("loginField");
        loginField.setAccessible(true);
        assertEquals("Логин", ((TextField) loginField.get(participantSettings1View)).getLabel());
        assertEquals(255, ((TextField) loginField.get(participantSettings1View)).getMaxLength());
        assertEquals("Login", ((TextField) loginField.get(participantSettings1View)).getElement().getAttribute("name"));
        assertEquals("Используйте только латинские буквы, цифры и символы -_.", ((TextField) loginField.get(participantSettings1View)).getErrorMessage());
        assertTrue(((TextField) loginField.get(participantSettings1View)).isReadOnly());
    }


}
