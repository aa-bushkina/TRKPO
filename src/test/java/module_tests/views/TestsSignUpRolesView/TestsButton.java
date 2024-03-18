package module_tests.views.TestsSignUpRolesView;

import com.cygans.security.db.RoleEnum;
import com.cygans.views.SignUpRolesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsButton {

    @InjectMocks
    private SignUpRolesView signUpRolesView;

    @Mock
    private VaadinSession vaadinSession;

    /**
     * Тестирование действия кнопки участника.
     */
    @Test
    public void testParticipantButtonClickAction() {
        VaadinSession.setCurrent(vaadinSession);
        // Arrange
        Button participantButton = signUpRolesView.getChildren()
                .filter(child -> child instanceof Button)
                .map(child -> (Button) child)
                .findFirst().orElse(null);

        // Act
        participantButton.click();


        verify(vaadinSession, times(1)).setAttribute("RoleEnum", RoleEnum.PARTICIPANT.getValue());
        verify(vaadinSession, never()).setAttribute("RoleEnum", RoleEnum.MENTOR.getValue());
    }

    /**
     * Тестирование действия кнопки ментора.
     */
    @Test
    public void testMentorButtonClickAction() {
        VaadinSession.setCurrent(vaadinSession);
        // Arrange
        Button mentorButton = signUpRolesView.getChildren()
                .filter(child -> child instanceof Button)
                .map(child -> (Button) child)
                .skip(1)
                .findFirst().orElse(null);

        // Act
        mentorButton.click();


        verify(vaadinSession, times(1)).setAttribute("RoleEnum", RoleEnum.MENTOR.getValue());
        verify(vaadinSession, never()).setAttribute("RoleEnum", RoleEnum.PARTICIPANT.getValue());
    }

}
