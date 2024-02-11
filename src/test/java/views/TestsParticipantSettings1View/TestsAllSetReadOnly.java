package views.TestsParticipantSettings1View;

import backTests.TestsParticipant.TestsParticipantModel.Variables;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsAllSetReadOnly {

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
     * Проверяем работу метода allSetReadOnly c true
     */
    @Test
    void test_allSetReadOnly_true() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(participantSettings1View, true);

        Field firstnameField = c.getDeclaredField("firstnameField");
        firstnameField.setAccessible(true);
        assertTrue(((TextField) firstnameField.get(participantSettings1View)).isReadOnly());
        Field lastnameField = c.getDeclaredField("lastnameField");
        lastnameField.setAccessible(true);
        assertTrue(((TextField) lastnameField.get(participantSettings1View)).isReadOnly());
        Field phoneField = c.getDeclaredField("phoneField");
        phoneField.setAccessible(true);
        assertTrue(((TextField) phoneField.get(participantSettings1View)).isReadOnly());
        Field heightField = c.getDeclaredField("heightField");
        heightField.setAccessible(true);
        assertTrue(((TextField) heightField.get(participantSettings1View)).isReadOnly());
        Field weightField = c.getDeclaredField("weightField");
        weightField.setAccessible(true);
        assertTrue(((TextField) weightField.get(participantSettings1View)).isReadOnly());
        Field breastField = c.getDeclaredField("breastField");
        breastField.setAccessible(true);
        assertTrue(((TextField) breastField.get(participantSettings1View)).isReadOnly());
        Field waistField = c.getDeclaredField("waistField");
        waistField.setAccessible(true);
        assertTrue(((TextField) waistField.get(participantSettings1View)).isReadOnly());
        Field hipsField = c.getDeclaredField("hipsField");
        hipsField.setAccessible(true);
        assertTrue(((TextField) hipsField.get(participantSettings1View)).isReadOnly());
        Field birthSelect = c.getDeclaredField("birthSelect");
        birthSelect.setAccessible(true);
        assertTrue(((DatePicker) birthSelect.get(participantSettings1View)).isReadOnly());
        Field genderSelect = c.getDeclaredField("genderSelect");
        genderSelect.setAccessible(true);
        assertTrue(((Select<String>) genderSelect.get(participantSettings1View)).isReadOnly());
    }

    /**
     * Проверяем работу метода allSetReadOnly c false
     */
    @Test
    void test_allSetReadOnly_false() throws Exception {

        Class c = ParticipantSettings1View.class;
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(participantSettings1View, false);

        Field firstnameField = c.getDeclaredField("firstnameField");
        firstnameField.setAccessible(true);
        assertFalse(((TextField) firstnameField.get(participantSettings1View)).isReadOnly());
        Field lastnameField = c.getDeclaredField("lastnameField");
        lastnameField.setAccessible(true);
        assertFalse(((TextField) lastnameField.get(participantSettings1View)).isReadOnly());
        Field phoneField = c.getDeclaredField("phoneField");
        phoneField.setAccessible(true);
        assertFalse(((TextField) phoneField.get(participantSettings1View)).isReadOnly());
        Field heightField = c.getDeclaredField("heightField");
        heightField.setAccessible(true);
        assertFalse(((TextField) heightField.get(participantSettings1View)).isReadOnly());
        Field weightField = c.getDeclaredField("weightField");
        weightField.setAccessible(true);
        assertFalse(((TextField) weightField.get(participantSettings1View)).isReadOnly());
        Field breastField = c.getDeclaredField("breastField");
        breastField.setAccessible(true);
        assertFalse(((TextField) breastField.get(participantSettings1View)).isReadOnly());
        Field waistField = c.getDeclaredField("waistField");
        waistField.setAccessible(true);
        assertFalse(((TextField) waistField.get(participantSettings1View)).isReadOnly());
        Field hipsField = c.getDeclaredField("hipsField");
        hipsField.setAccessible(true);
        assertFalse(((TextField) hipsField.get(participantSettings1View)).isReadOnly());
        Field birthSelect = c.getDeclaredField("birthSelect");
        birthSelect.setAccessible(true);
        assertFalse(((DatePicker) birthSelect.get(participantSettings1View)).isReadOnly());
        Field genderSelect = c.getDeclaredField("genderSelect");
        genderSelect.setAccessible(true);
        assertFalse(((Select<String>) genderSelect.get(participantSettings1View)).isReadOnly());
    }

}
