package module.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.UI;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsChangeSettingInit {

    @Mock
    private SettingsController settingsController;

    @Mock
    private UI ui;

    private ParticipantSettings1View participantSettings1View;

    @BeforeEach
    void before() {
        Participant participant = new Participant(Variables.FIRST_NAME, Variables.LAST_NAME, Variables.LOGIN, Variables.PHONE, Variables.GENDER, Variables.BIRTHDAY, Variables.HEIGHT,
                Variables.WEIGHT, Variables.HIPS, Variables.WAIST, Variables.BREAST, Variables.LOGIN_INFO_ID);
        when(settingsController.getAuthoritiesParticipant()).thenReturn(participant);
        participantSettings1View = new ParticipantSettings1View(settingsController);
    }

    /**
     * Проверяем работу метода changeSettingInit
     */
    @Test
    void test_changeSettingInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("changeSettingInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field changeSetting = c.getDeclaredField("changeSetting");
        changeSetting.setAccessible(true);
        assertEquals("Редактировать", ((Button) changeSetting.get(participantSettings1View)).getText());
        assertEquals("primary", ((Button) changeSetting.get(participantSettings1View)).getThemeName());
        assertEquals("auto", ((Button) changeSetting.get(participantSettings1View)).getElement().getStyle().get("margin-left"));
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) changeSetting.get(participantSettings1View)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        Field field = c.getDeclaredField("changeSetting");
        field.setAccessible(true);
        assertTrue(((Button) field.get(participantSettings1View)).isVisible());
        field = c.getDeclaredField("changePassword");
        field.setAccessible(true);
        assertTrue(((Button) field.get(participantSettings1View)).isVisible());
        field = c.getDeclaredField("save");
        field.setAccessible(true);
        assertFalse(((Button) field.get(participantSettings1View)).isVisible());
        field = c.getDeclaredField("cancel");
        field.setAccessible(true);
        assertFalse(((Button) field.get(participantSettings1View)).isVisible());
    }

}
