package views.TestsMentorSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.mentor.settings.MentorSettings2View;
import com.cygans.views.participant.settings.ParticipantSettings2View;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestsSaveBtnInit {

    @Mock
    private SettingsController settingsController;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода saveBtnInit
     */
    @Test
    void test_saveBtnInit() throws Exception {

        Class c = MentorSettings2View.class;
        Method method = c.getDeclaredMethod("saveBtnInit");
        method.setAccessible(true);
        method.invoke(mentorSettings2View);

        Field confirmButton = c.getDeclaredField("confirmButton");
        confirmButton.setAccessible(true);
        assertEquals("Сохранить", ((Button) confirmButton.get(mentorSettings2View)).getText());
        assertEquals("primary", ((Button) confirmButton.get(mentorSettings2View)).getThemeName());
        assertEquals("1em", ((Button) confirmButton.get(mentorSettings2View)).getElement().getStyle().get("margin-left"));
    }

}