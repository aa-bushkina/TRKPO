package module_tests.views.TestsParticipantSignUp3View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.participant.signup.ParticipantSignUp3View;
import com.vaadin.flow.component.textfield.TextField;
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
public class TestsWeightSetUp {

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
     * Тест для метода weightSetUp.
     * Проверяет, что поле для веса правильно настроено.
     */
    @Test
    void testWeightSetUp() throws Exception {
        Class cl = ParticipantSignUp3View.class;
        Method method = cl.getDeclaredMethod("weightSetUp");
        method.setAccessible(true);
        method.invoke(participantSignUp3View);
        Field field = cl.getDeclaredField("weight");
        field.setAccessible(true);
        assertNotNull(field.get(participantSignUp3View));
        assertEquals("Вес", ((TextField) field.get(participantSignUp3View)).getLabel());
        assertTrue(((TextField) field.get(participantSignUp3View)).isClearButtonVisible());
        assertEquals(3, ((TextField) field.get(participantSignUp3View)).getMaxLength());
    }

}
