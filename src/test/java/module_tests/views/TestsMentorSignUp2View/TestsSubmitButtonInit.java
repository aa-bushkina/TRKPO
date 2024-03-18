package module_tests.views.TestsMentorSignUp2View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.mentor.signup.MentorSignUp2View;
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
     * Тест для метода submitButtonInit.
     * Проверяет, что кнопка "Завершить" правильно инициализирована.
     */
    @Test
    void testSubmitButtonInit() throws Exception {
        Class cl = MentorSignUp2View.class;
        Method method = cl.getDeclaredMethod("submitButtonInit");
        method.setAccessible(true);
        method.invoke(mentorSignUp2View);
        Field field = cl.getDeclaredField("nextBtn");
        field.setAccessible(true);
        assertNotNull(field.get(mentorSignUp2View));
        assertTrue(((Button) field.get(mentorSignUp2View)).isEnabled());
    }

}
