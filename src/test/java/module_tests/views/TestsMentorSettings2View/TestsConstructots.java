package module_tests.views.TestsMentorSettings2View;
import com.cygans.database.controllers.SettingsController;
import com.cygans.views.mentor.settings.MentorSettings2View;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestsConstructots {

    @Mock
    private SettingsController settingsController;

    private MentorSettings2View mentorSettings2View;

    @BeforeEach
    void before() {
        mentorSettings2View = new MentorSettings2View(settingsController);
    }

    /**
     * Проверяем работу конструктора
     */
    @Test
    void test_constructors() throws Exception {

        Class c = MentorSettings2View.class;
        Field mainLayout = c.getDeclaredField("mainLayout");
        mainLayout.setAccessible(true);
        assertEquals(6, ((VerticalLayout) mainLayout.get(mentorSettings2View)).getElement().getChildCount());
    }


}