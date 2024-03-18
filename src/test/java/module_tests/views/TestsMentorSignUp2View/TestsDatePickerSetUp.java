package module_tests.views.TestsMentorSignUp2View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.mentor.signup.MentorSignUp2View;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsDatePickerSetUp {

    @Mock
    private VaadinSession vaadinSessionMock;

    @Mock
    private RegistrationAndLoginController registrationAndLoginControllerMock;

    private MentorSignUp2View mentorSignUp2View;

    @BeforeEach
    public void setUp() {
        vaadinSessionMock.setAttribute("Gender", "Male");
        VaadinSession.setCurrent(vaadinSessionMock);
        mentorSignUp2View = new MentorSignUp2View(registrationAndLoginControllerMock);
    }

    /**
     * Тест для метода datePickerSetUp.
     * Проверяет, что элемент выбора даты правильно настроен.
     */
    @Test
    void testDatePickerSetUp() throws Exception {
        Class cl = MentorSignUp2View.class;
        Method method = cl.getDeclaredMethod("datePickerSetUp");
        method.setAccessible(true);
        method.invoke(mentorSignUp2View);
        Field field = cl.getDeclaredField("datePicker");
        field.setAccessible(true);
        assertNotNull(field.get(mentorSignUp2View));
        assertEquals("Дата рождения", ((DatePicker) field.get(mentorSignUp2View)).getLabel());
        assertTrue(((DatePicker) field.get(mentorSignUp2View)).isClearButtonVisible());
        assertNotNull(((DatePicker) field.get(mentorSignUp2View)).getLocale());
        assertEquals("ДД.ММ.ГГГГ", ((DatePicker) field.get(mentorSignUp2View)).getPlaceholder());
    }

}
