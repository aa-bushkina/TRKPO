package views.TestsSignUp1View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.SignUp1View;
import com.vaadin.flow.component.textfield.PasswordField;
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
public class TestsConfirmPasswordSetUp {

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
     * Проверяем, что настройка поля для повторного ввода пароля происходит корректно
     */
    @Test
    void confirmPasswordSetUp_checkConfirmPasswordProperties() throws Exception {
        Class cl = SignUp1View.class;
        Method method = cl.getDeclaredMethod("confirmPasswordSetUp");
        method.setAccessible(true);
        method.invoke(signUp1View);
        Field field = cl.getDeclaredField("confirmPassword");
        field.setAccessible(true);
        assertNotNull(field.get(signUp1View));
        assertEquals("Повторите пароль", ((PasswordField) field.get(signUp1View)).getLabel());
        assertTrue(((PasswordField) field.get(signUp1View)).isClearButtonVisible());
        assertEquals("Пароль", ((PasswordField) field.get(signUp1View)).getPlaceholder());
    }

}
