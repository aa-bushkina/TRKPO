package module_tests.views.TestsParticipantSignUp2View;

import com.cygans.views.participant.signup.ParticipantSignUp2View;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TestsDatePickerSetUp {

    @Mock
    private VaadinSession vaadinSession;

    private ParticipantSignUp2View participantSignUp2View;

    @BeforeEach
    public void setUp() {
        vaadinSession.setAttribute("Gender", "Male");
        VaadinSession.setCurrent(vaadinSession);
        participantSignUp2View = new ParticipantSignUp2View();
    }

    /**
     * Тест для метода datePickerSetUp.
     * Проверяет, что выбор даты правильно настроен.
     */
    @Test
    void testDatePickerSetUp() throws Exception {
        LocalDate now = LocalDate.now();
        Class cl = ParticipantSignUp2View.class;
        Method method = cl.getDeclaredMethod("datePickerSetUp");
        method.setAccessible(true);
        method.invoke(participantSignUp2View);
        Field field = cl.getDeclaredField("datePicker");
        field.setAccessible(true);
        assertNotNull(field.get(participantSignUp2View));
        assertEquals(now, ((DatePicker) field.get(participantSignUp2View)).getMax());
    }

}
