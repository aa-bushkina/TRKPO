package module_tests.views.TestsParticipantSettings1View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.datepicker.DatePicker;
import module_tests.backTests.TestsParticipant.TestsParticipantModel.Variables;
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
public class TestsBirthSelectInit {

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
        Method method = c.getDeclaredMethod("birthSelectInit");
        method.setAccessible(true);
        method.invoke(participantSettings1View);

        Field birthSelect = c.getDeclaredField("birthSelect");
        birthSelect.setAccessible(true);
        assertEquals("Дата рождения", ((DatePicker) birthSelect.get(participantSettings1View)).getLabel());
        assertEquals(new Locale("ru", "RU"), ((DatePicker) birthSelect.get(participantSettings1View)).getLocale());
        assertEquals(LocalDate.now(ZoneId.systemDefault()), ((DatePicker) birthSelect.get(participantSettings1View)).getMax());
        assertEquals(LocalDate.now(ZoneId.systemDefault()).minusYears(100), ((DatePicker) birthSelect.get(participantSettings1View)).getMin());
        assertEquals("ДД.ММ.ГГГГ", ((DatePicker) birthSelect.get(participantSettings1View)).getPlaceholder());
        assertTrue(((DatePicker) birthSelect.get(participantSettings1View)).isReadOnly());
    }

}
