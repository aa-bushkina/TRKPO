package views.TestsStartView;

import com.cygans.views.StartView;
//import com.cygans.views.util.Filler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

//    @Mock
//    private Filler filler;

    @InjectMocks
    private StartView startView;

    /**
     * Тестирование конструктора StartView.
     */
    @Test
    public void testConstructor() {
        assertNotNull(startView);
        assertTrue(startView.getChildren().anyMatch(child -> child instanceof Image));
        assertTrue(startView.getChildren().anyMatch(child -> child instanceof LoginForm));
        assertTrue(startView.getChildren().anyMatch(child -> child instanceof Button));
        LoginForm loginForm = startView.getChildren()
                .filter(child -> child instanceof LoginForm)
                .map(child -> (LoginForm) child)
                .findFirst().orElse(null);
        assertNotNull(loginForm);
        assertFalse(loginForm.isForgotPasswordButtonVisible());
    }

}
