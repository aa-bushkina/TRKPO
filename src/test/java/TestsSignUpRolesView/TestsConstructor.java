package TestsSignUpRolesView;

import com.cygans.security.db.RoleEnum;
import com.cygans.views.SignUpRolesView;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @InjectMocks
    private SignUpRolesView signUpRolesView;

    /**
     * Тестирование конструктора SignUpRolesView.
     */
    @Test
    public void testConstructor() {
        // Assert
        assertNotNull(signUpRolesView);
        assertTrue(signUpRolesView.getChildren().anyMatch(child -> child instanceof Toolbar));
        assertTrue(signUpRolesView.getChildren().anyMatch(child -> child instanceof H2));
        assertTrue(signUpRolesView.getChildren().anyMatch(child -> child instanceof Button));

        Button participantButton = signUpRolesView.getChildren()
                .filter(child -> child instanceof Button)
                .map(child -> (Button) child)
                .findFirst().orElse(null);
        assertNotNull(participantButton);
        assertEquals("Участник марафона", participantButton.getText(), "Неправильный текст кнопки для участника");
        assertEquals("primary", participantButton.getThemeName(), "Неправильный стиль кнопки для участника");

        Button mentorButton = signUpRolesView.getChildren()
                .filter(child -> child instanceof Button)
                .map(child -> (Button) child)
                .skip(1)
                .findFirst().orElse(null);
        assertNotNull(mentorButton);
        assertEquals("Ментор", mentorButton.getText(), "Неправильный текст кнопки для ментора");
        assertEquals("primary", mentorButton.getThemeName(), "Неправильный стиль кнопки для ментора");
    }

}
