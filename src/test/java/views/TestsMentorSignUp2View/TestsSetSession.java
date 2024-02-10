package views.TestsMentorSignUp2View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.mentor.signup.MentorSignUp2View;
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

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsSetSession {

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
     * Тест для метода setSession.
     * Проверяет, что атрибуты сессии правильно установлены.
     */
    @Test
    void testSetSession() throws Exception {
        Class cl = MentorSignUp2View.class;
        Method method = cl.getDeclaredMethod("setSession");
        method.setAccessible(true);
        method.invoke(mentorSignUp2View);
        Field sex = cl.getDeclaredField("sex");
        sex.setAccessible(true);
        Field phone = cl.getDeclaredField("phone");
        phone.setAccessible(true);
        Field datePicker = cl.getDeclaredField("datePicker");
        datePicker.setAccessible(true);

        verify(vaadinSessionMock).setAttribute("Gender", ((RadioButtonGroup<String>) sex.get(mentorSignUp2View)).getValue());
        verify(vaadinSessionMock).setAttribute("Phone", ((TextField) phone.get(mentorSignUp2View)).getValue());
        verify(vaadinSessionMock).setAttribute("Date", ((DatePicker) datePicker.get(mentorSignUp2View)).getValue());
    }

}
