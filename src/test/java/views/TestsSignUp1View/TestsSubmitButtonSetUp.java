package views.TestsSignUp1View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.SignUp1View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
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
public class TestsSubmitButtonSetUp {

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
     * Проверяем, что настройка кнопки "Далее" происходит корректно
     */
    @Test
    void submitButtonSetUp_checkSubmitButtonProperties() throws Exception{
        Class cl = SignUp1View.class;
        Method method = cl.getDeclaredMethod("submitButtonSetUp");
        method.setAccessible(true);
        method.invoke(signUp1View);
        Field field = cl.getDeclaredField("nextBtn");
        field.setAccessible(true);
        assertNotNull(field.get(signUp1View));
        assertEquals("Далее", ((Button) field.get(signUp1View)).getText());
        assertTrue(((Button) field.get(signUp1View)).getThemeNames().contains(ButtonVariant.LUMO_PRIMARY.getVariantName()));
        assertTrue(((Button) field.get(signUp1View)).getElement().getStyle().has("margin-left"));
    }

}
