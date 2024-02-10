package views.TestsParticipantSignUp2View;

import com.cygans.views.participant.signup.ParticipantSignUp2View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsSubmitButtonInit {

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
     * Тест для метода submitButtonInit.
     * Проверяет, что кнопка отправки правильно инициализирована.
     */
    @Test
    void testSubmitButtonInit() throws Exception {
        Class cl = ParticipantSignUp2View.class;
        Method method = cl.getDeclaredMethod("submitButtonInit");
        method.setAccessible(true);
        method.invoke(participantSignUp2View);
        Field field = cl.getDeclaredField("nextBtn");
        field.setAccessible(true);
        assertNotNull(field.get(participantSignUp2View));
        assertTrue(((Button) field.get(participantSignUp2View)).isEnabled());
    }

}
