package module.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.button.Button;
import module.backTests.TestsParticipant.TestsParticipantModel.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsChangePasswordInit {

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
     * Проверяем работу метода changePasswordInit
     */
    @Test
    void test_changePasswordInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("changePasswordInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field changePassword = c.getDeclaredField("changePassword");
        changePassword.setAccessible(true);
        assertEquals("Изменить пароль", ((Button) changePassword.get(participantSettings1View)).getText());
        assertEquals("primary", ((Button) changePassword.get(participantSettings1View)).getThemeName());

        Field save = c.getDeclaredField("save");
        save.setAccessible(true);
        assertEquals("auto", ((Button) save.get(participantSettings1View)).getElement().getStyle().get("margin-right"));
        ((Button) changePassword.get(participantSettings1View)).click();
    }


}
