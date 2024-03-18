package module_tests.views.TestsParticipantSignUp2View;

import com.cygans.views.participant.signup.ParticipantSignUp2View;
import com.vaadin.flow.component.formlayout.FormLayout;
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


@ExtendWith(MockitoExtension.class)
public class TestsFormlayoutSetUp {

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
     * Тест для метода formlayoutSetUp.
     * Проверяет, что компоненты формы правильно настроены.
     */
    @Test
    void testFormlayoutSetUp() throws Exception {
        Class cl = ParticipantSignUp2View.class;
        Method method = cl.getDeclaredMethod("formlayoutSetUp");
        method.setAccessible(true);
        method.invoke(participantSignUp2View);
        Field field = cl.getDeclaredField("formLayout");
        field.setAccessible(true);
        assertNotNull(field.get(participantSignUp2View));
        assertEquals(3, ((FormLayout)field.get(participantSignUp2View)).getChildren().count());
    }

}
