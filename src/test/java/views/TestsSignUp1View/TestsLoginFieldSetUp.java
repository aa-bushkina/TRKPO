package views.TestsSignUp1View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.SignUp1View;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsLoginFieldSetUp {

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
     *  Проверяем, что настройка поля "Логин" происходит корректно
     */
    @Test
    void loginFieldSetUp_checkLoginFieldProperties() throws Exception {
        Class cl = SignUp1View.class;
        Method method = cl.getDeclaredMethod("loginFieldSetUp");
        method.setAccessible(true);
        method.invoke(signUp1View);
        Field field = cl.getDeclaredField("login");
        field.setAccessible(true);
        assertNotNull(field.get(signUp1View));
        assertEquals("Логин", ((TextField) field.get(signUp1View)).getLabel());
        assertTrue(((TextField) field.get(signUp1View)).isClearButtonVisible());
        assertEquals("Логин", ((TextField) field.get(signUp1View)).getPlaceholder());
        assertEquals(255, ((TextField) field.get(signUp1View)).getMaxLength());
        assertEquals("Используйте только латинские буквы, цифры и символы -_.", ((TextField) field.get(signUp1View)).getErrorMessage());
    }

}
