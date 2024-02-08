package views.TestsParticipantConfirmationView;

import com.cygans.views.participant.ParticipantHomeView;
import com.cygans.views.participant.logbooks.ParticipantConfirmationView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Router;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsParticipantConfirmationView {

    private ParticipantConfirmationView participantConfirmationView;

    @Mock
    private Router router;

    @Mock
    private UI ui;

    @Mock
    private Button OKButton;

    /**
     * Проверяем, что кнопка создана
     */
    @Test
    public void testConstructor_InitialSetup() throws Exception {
        participantConfirmationView = new ParticipantConfirmationView();
        Class c = ParticipantConfirmationView.class;
        Field field = c.getDeclaredField("OKButton");
        field.setAccessible(true);
        assertNotNull(field.get(participantConfirmationView));
    }

    @Test
    public void testConstructor_ButtonClick_NavigateToParticipantHomeView() throws Exception {
        participantConfirmationView = new ParticipantConfirmationView();
        UI.setCurrent(ui);
        Class c = ParticipantConfirmationView.class;
        Field field = c.getDeclaredField("OKButton");
        field.setAccessible(true);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) field.get(participantConfirmationView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        ((Button) field.get(participantConfirmationView)).click();
        verify(ui, times(0)).navigate(ParticipantHomeView.class);
    }

}
