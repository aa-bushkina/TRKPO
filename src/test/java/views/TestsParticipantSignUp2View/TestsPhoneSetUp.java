package views.TestsParticipantSignUp2View;

import com.cygans.views.participant.signup.ParticipantSignUp2View;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsPhoneSetUp {

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
     * Тест для метода phoneSetUp.
     * Проверяет, что поле для телефона правильно настроено.
     */
    @Test
    void testPhoneSetUp() throws Exception {
        Class cl = ParticipantSignUp2View.class;
        Method method = cl.getDeclaredMethod("phoneSetUp");
        method.setAccessible(true);
        method.invoke(participantSignUp2View);
        Field field = cl.getDeclaredField("phone");
        field.setAccessible(true);
        assertNotNull(field.get(participantSignUp2View));
        assertTrue(((TextField) field.get(participantSignUp2View)).isVisible());
        assertEquals("Номер телефона", ((TextField) field.get(participantSignUp2View)).getLabel());
    }

}
