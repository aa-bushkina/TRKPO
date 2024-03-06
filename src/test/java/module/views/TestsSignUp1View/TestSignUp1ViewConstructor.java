package module.views.TestsSignUp1View;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.views.SignUp1View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestSignUp1ViewConstructor {

    @Test
    void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        RegistrationAndLoginController registrationAndLoginControllerMock = mock(RegistrationAndLoginController.class);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        when(vaadinSessionMock.getAttribute("FirstName")).thenReturn("John");
        when(vaadinSessionMock.getAttribute("LastName")).thenReturn("Doe");
        when(vaadinSessionMock.getAttribute("Login")).thenReturn("johndoe");
        when(vaadinSessionMock.getAttribute("Password")).thenReturn("password");

        VaadinSession.setCurrent(vaadinSessionMock);

        SignUp1View signUp1View = new SignUp1View(registrationAndLoginControllerMock);

        Field registrationAndLoginControllerField = SignUp1View.class.getDeclaredField("registrationAndLoginController");
        registrationAndLoginControllerField.setAccessible(true);
        RegistrationAndLoginController controller = (RegistrationAndLoginController) registrationAndLoginControllerField.get(signUp1View);
        assertNotNull(controller);

        Field firstNameField = SignUp1View.class.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        TextField firstName = (TextField) firstNameField.get(signUp1View);
        assertNotNull(firstName);

        Field lastNameField = SignUp1View.class.getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        TextField lastName = (TextField) lastNameField.get(signUp1View);
        assertNotNull(lastName);

        Field loginField = SignUp1View.class.getDeclaredField("login");
        loginField.setAccessible(true);
        TextField login = (TextField) loginField.get(signUp1View);
        assertNotNull(login);

        Field passwordField = SignUp1View.class.getDeclaredField("password");
        passwordField.setAccessible(true);
        PasswordField password = (PasswordField) passwordField.get(signUp1View);
        assertNotNull(password);

        Field confirmPasswordField = SignUp1View.class.getDeclaredField("confirmPassword");
        confirmPasswordField.setAccessible(true);
        PasswordField confirmPassword = (PasswordField) confirmPasswordField.get(signUp1View);
        assertNotNull(confirmPassword);

        Field nextBtnField = SignUp1View.class.getDeclaredField("nextBtn");
        nextBtnField.setAccessible(true);
        Button nextBtn = (Button) nextBtnField.get(signUp1View);
        assertNotNull(nextBtn);

        Field formLayoutField = SignUp1View.class.getDeclaredField("formLayout");
        formLayoutField.setAccessible(true);
        FormLayout formLayout = (FormLayout) formLayoutField.get(signUp1View);
        assertNotNull(formLayout);

        Field mainLayoutField = SignUp1View.class.getDeclaredField("mainLayout");
        mainLayoutField.setAccessible(true);
        VerticalLayout mainLayout = (VerticalLayout) mainLayoutField.get(signUp1View);
        assertNotNull(mainLayout);
    }
}
