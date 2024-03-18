package module_tests.views.TestsMentorSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.mentor.settings.MentorSettings2View;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestMainLayoutInit {

    @Mock
    private SettingsController settingsController;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода mainLayoutInit
     */
    @Test
    void test_mainLayoutInit() throws Exception {

        Class c = MentorSettings2View.class;
        Method method = c.getDeclaredMethod("mainLayoutInit");
        method.setAccessible(true);
        method.invoke(mentorSettings2View);

        Field mainLayout = c.getDeclaredField("mainLayout");
        mainLayout.setAccessible(true);
        assertEquals("500px", ((VerticalLayout) mainLayout.get(mentorSettings2View)).getMaxWidth());
        assertEquals(FlexComponent.Alignment.STRETCH, ((VerticalLayout) mainLayout.get(mentorSettings2View)).getAlignItems());
        assertEquals(FlexComponent.JustifyContentMode.CENTER, ((VerticalLayout) mainLayout.get(mentorSettings2View)).getJustifyContentMode());
    }

}