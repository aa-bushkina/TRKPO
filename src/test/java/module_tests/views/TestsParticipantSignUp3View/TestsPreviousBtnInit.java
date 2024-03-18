package module_tests.views.TestsParticipantSignUp3View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.participant.signup.ParticipantSignUp3View;
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
public class TestsPreviousBtnInit {
    @Mock
    private VaadinSession vaadinSession;

    @Mock
    private RegistrationAndLoginController registrationAndLoginControllerMock;

    private ParticipantSignUp3View participantSignUp3View;

    @BeforeEach
    public void setUp() {
        VaadinSession.setCurrent(vaadinSession);
        participantSignUp3View = new ParticipantSignUp3View(registrationAndLoginControllerMock);
    }

    /**
     * Тест для метода previousBtnInit.
     * Проверяет, что кнопка "Назад" правильно инициализирована.
     */
    @Test
    void testPreviousBtnInit() throws Exception {
        Class cl = ParticipantSignUp3View.class;
        Method method = cl.getDeclaredMethod("previousBtnInit");
        method.setAccessible(true);
        method.invoke(participantSignUp3View);
        Field field = cl.getDeclaredField("previousBtn");
        field.setAccessible(true);
        assertNotNull(field.get(participantSignUp3View));
        assertNotNull(field.get(participantSignUp3View));
        assertTrue(((Button) field.get(participantSignUp3View)).isEnabled());
    }

}
