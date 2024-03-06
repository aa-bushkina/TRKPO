package module.views.TestsMentorSignUp2View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.mentor.signup.MentorSignUp2View;
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
public class TeststFormlayoutSetUp {

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
     * Тест для метода formlayoutSetUp.
     * Проверяет, что форматирование элементов формы правильно установлено.
     */
    @Test
    void testFormlayoutSetUp() throws Exception {
        Class cl = MentorSignUp2View.class;
        Method method = cl.getDeclaredMethod("formlayoutSetUp");
        method.setAccessible(true);
        method.invoke(mentorSignUp2View);
        Field field = cl.getDeclaredField("formLayout");
        field.setAccessible(true);

        assertNotNull((FormLayout) field.get(mentorSignUp2View));
        assertEquals(3, ((FormLayout) field.get(mentorSignUp2View)).getChildren().count());
    }

}
