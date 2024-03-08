package module.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import module.backTests.TestsParticipant.TestsParticipantModel.Variables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsCancelInit {

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
     * Проверяем работу метода cancelInit
     */
    @Test
    void test_cancelInit() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("cancelInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field cancel = c.getDeclaredField("cancel");
        cancel.setAccessible(true);
        assertEquals("Отменить", ((Button) cancel.get(participantSettings1View)).getText());
        assertFalse(((Button) cancel.get(participantSettings1View)).isVisible());
        assertEquals("auto", ((Button) cancel.get(participantSettings1View)).getElement().getStyle().get("margin-right"));
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) cancel.get(participantSettings1View)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        Field field = c.getDeclaredField("firstnameField");
        field.setAccessible(true);
        Field field1 = c.getDeclaredField("firstname");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantSettings1View), ((TextField) field.get(participantSettings1View)).getValue());
        field = c.getDeclaredField("lastnameField");
        field.setAccessible(true);
        field1 = c.getDeclaredField("lastname");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantSettings1View), ((TextField) field.get(participantSettings1View)).getValue());
        field = c.getDeclaredField("birthSelect");
        field.setAccessible(true);
        field1 = c.getDeclaredField("birth");
        field1.setAccessible(true);
        assertEquals((LocalDate) field1.get(participantSettings1View), ((DatePicker) field.get(participantSettings1View)).getValue());
        field = c.getDeclaredField("loginField");
        field.setAccessible(true);
        field1 = c.getDeclaredField("login");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantSettings1View), ((TextField) field.get(participantSettings1View)).getValue());
        field = c.getDeclaredField("phoneField");
        field.setAccessible(true);
        field1 = c.getDeclaredField("phone");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantSettings1View), ((TextField) field.get(participantSettings1View)).getValue());
        field = c.getDeclaredField("genderSelect");
        field.setAccessible(true);
        assertEquals("Female", ((Select<String>) field.get(participantSettings1View)).getValue());
        field = c.getDeclaredField("changeSetting");
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