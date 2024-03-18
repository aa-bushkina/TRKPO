package module_tests.views.TestsParticipantSettings2View;

import com.cygans.database.controllers.SettingsController;
import com.cygans.views.participant.settings.ParticipantSettings2View;
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
public class TestsMainLayoutInit {

    @Mock
    private SettingsController settingsController;

    private ParticipantSettings2View participantSettings2View;

    @BeforeEach
    void before() {
        participantSettings2View = new ParticipantSettings2View(settingsController);
    }

    /**
     * Проверяем работу метода mainLayoutInit
     */
    @Test
    void test_mainLayoutInit() throws Exception {

        Class c = ParticipantSettings2View.class;
        Method method = c.getDeclaredMethod("mainLayoutInit");
        method.setAccessible(true);
        method.invoke(participantSettings2View);

        Field mainLayout = c.getDeclaredField("mainLayout");
        mainLayout.setAccessible(true);
        assertEquals("500px", ((VerticalLayout) mainLayout.get(participantSettings2View)).getMaxWidth());
        assertEquals(FlexComponent.Alignment.STRETCH, ((VerticalLayout) mainLayout.get(participantSettings2View)).getAlignItems());
        assertEquals(FlexComponent.JustifyContentMode.CENTER, ((VerticalLayout) mainLayout.get(participantSettings2View)).getJustifyContentMode());
    }

}
