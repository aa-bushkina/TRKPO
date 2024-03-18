package module_tests.views.TestsMentorSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.mentor.settings.MentorSettings2View;
import com.vaadin.flow.component.UI;
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
public class TestsCancelBtnInit {

    @Mock
    private SettingsController settingsController;

    @Mock
    private UI ui;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода cancelBtnInit
     */
    @Test
    void test_cancelBtnInit() throws Exception {

        Class c = MentorSettings2View.class;
        Method method = c.getDeclaredMethod("cancelBtnInit");
        method.setAccessible(true);
        method.invoke(mentorSettings2View);

        Field cancel = c.getDeclaredField("cancelButton");
        cancel.setAccessible(true);
        assertEquals("Отменить", ((Button) cancel.get(mentorSettings2View)).getText());
        assertEquals("auto", ((Button) cancel.get(mentorSettings2View)).getElement().getStyle().get("margin-right"));
        ((Button) cancel.get(mentorSettings2View)).click();
    }
}