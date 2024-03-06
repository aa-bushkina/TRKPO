package module.views.TestsMentorSettings1View;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.vaadin.flow.component.datepicker.DatePicker;
import module.backTests.TestsParticipant.TestsParticipantModel.Variables;
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

    private MentorSettings1View mentorSettings1View;

    @BeforeEach
    void before() {
        Mentor mentor = new Mentor(Variables.FIRST_NAME, Variables.LAST_NAME, Variables.LOGIN, Variables.PHONE, Variables.GENDER, Variables.BIRTHDAY, Variables.LOGIN_INFO_ID);
        when(settingsController.getAuthoritiesMentor()).thenReturn(mentor);
        mentorSettings1View = new MentorSettings1View(settingsController);
    }

    /**
     * Проверяем работу метода birthSelectInit
     */
    @Test
    void test_birthSelectInit() throws Exception {

        Class c = MentorSettings1View.class;
        Method method = c.getDeclaredMethod("birthSelectInit");
        method.setAccessible(true);
        method.invoke(mentorSettings1View);

        Field birthSelect = c.getDeclaredField("birthSelect");
        birthSelect.setAccessible(true);
        assertEquals("Дата рождения", ((DatePicker) birthSelect.get(mentorSettings1View)).getLabel());
        assertEquals(new Locale("ru", "RU"), ((DatePicker) birthSelect.get(mentorSettings1View)).getLocale());
        assertEquals(LocalDate.now(ZoneId.systemDefault()), ((DatePicker) birthSelect.get(mentorSettings1View)).getMax());
        assertEquals(LocalDate.now(ZoneId.systemDefault()).minusYears(100), ((DatePicker) birthSelect.get(mentorSettings1View)).getMin());
        assertEquals("ДД.ММ.ГГГГ", ((DatePicker) birthSelect.get(mentorSettings1View)).getPlaceholder());
        assertTrue(((DatePicker) birthSelect.get(mentorSettings1View)).isReadOnly());
    }

}
