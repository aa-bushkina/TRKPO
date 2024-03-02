package views.TestsParticipantSignUp3View;

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

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsSetSession {

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
     * Тест для метода setSession.
     * Проверяет, что атрибуты сессии правильно установлены.
     */
    @Test
    void testSetSession() throws Exception {
        Class cl = ParticipantSignUp3View.class;
        Method method = cl.getDeclaredMethod("setSession");
        method.setAccessible(true);
        method.invoke(participantSignUp3View);
        Field height = cl.getDeclaredField("height");
        height.setAccessible(true);
        Field weight = cl.getDeclaredField("weight");
        weight.setAccessible(true);
        Field waist = cl.getDeclaredField("waist");
        waist.setAccessible(true);
        Field breast = cl.getDeclaredField("breast");
        breast.setAccessible(true);
        Field hips = cl.getDeclaredField("hips");
        hips.setAccessible(true);
        verify(vaadinSession).setAttribute("Height", ((TextField) height.get(participantSignUp3View)).getValue());
        verify(vaadinSession).setAttribute("Weight", ((TextField) weight.get(participantSignUp3View)).getValue());
        verify(vaadinSession).setAttribute("Breast", ((TextField) breast.get(participantSignUp3View)).getValue());
        verify(vaadinSession).setAttribute("Waist", ((TextField) waist.get(participantSignUp3View)).getValue());
        verify(vaadinSession).setAttribute("Hip", ((TextField) hips.get(participantSignUp3View)).getValue());
    }

}
