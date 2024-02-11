package views.TestsSignUp1View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.SignUp1View;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsMainLayoutSetUp {

    @Mock
    private RegistrationAndLoginController registrationAndLoginController;
    private SignUp1View signUp1View;

    @BeforeEach
    void before() {
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        when(vaadinSessionMock.getAttribute("FirstName")).thenReturn("John");
        when(vaadinSessionMock.getAttribute("LastName")).thenReturn("Doe");
        when(vaadinSessionMock.getAttribute("Login")).thenReturn("johndoe");
        when(vaadinSessionMock.getAttribute("Password")).thenReturn("password");
        VaadinSession.setCurrent(vaadinSessionMock);
        signUp1View = new SignUp1View(registrationAndLoginController);
    }

    /**
     *  Проверяем, что настройка основного макета происходит корректно
     */
    @Test
    void mainLayoutSetUp_checkMainLayoutProperties() throws Exception{
        Class cl = SignUp1View.class;
        Method method = cl.getDeclaredMethod("mainLayoutSetUp");
        method.setAccessible(true);
        method.invoke(signUp1View);
        Field field = cl.getDeclaredField("mainLayout");
        field.setAccessible(true);
        assertNotNull(field.get(signUp1View));
        assertEquals("600px", ((VerticalLayout) field.get(signUp1View)).getMaxWidth());
        assertFalse(((VerticalLayout) field.get(signUp1View)).isPadding());
    }


}
