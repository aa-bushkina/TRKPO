package views.TestsParticipantSignUp2View;

import com.cygans.views.participant.signup.ParticipantSignUp2View;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestsSetSession {

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
     * Тест для метода setSession.
     * Проверяет, что атрибуты сессии правильно установлены.
     */
    @Test
    void testSetSession() throws Exception {
        Class cl = ParticipantSignUp2View.class;
        Method method = cl.getDeclaredMethod("setSession");
        method.setAccessible(true);
        method.invoke(participantSignUp2View);

        Field gender = cl.getDeclaredField("gender");
        gender.setAccessible(true);
        Field phone = cl.getDeclaredField("phone");
        phone.setAccessible(true);
        Field datePicker = cl.getDeclaredField("datePicker");
        datePicker.setAccessible(true);

        assertEquals(((RadioButtonGroup<String>) gender.get(participantSignUp2View)).getValue(), VaadinSession.getCurrent().getAttribute("Gender"));
        assertNull(VaadinSession.getCurrent().getAttribute("Phone"));
        assertEquals(((DatePicker) datePicker.get(participantSignUp2View)).getValue(), VaadinSession.getCurrent().getAttribute("Date"));
    }

}
