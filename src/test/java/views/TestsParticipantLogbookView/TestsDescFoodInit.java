package views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsDescFoodInit {

    @Mock
    private LogController logController;

    @Mock
    private VaadinSession vaadinSession;

    private ParticipantLogbookView participantLogbookView;

    @BeforeEach
    void before() {
        VaadinSession.setCurrent(vaadinSession);
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("LogbookType");
        when(vaadinSession.getAttribute("LogbookId")).thenReturn(1L);
        when(vaadinSession.getAttribute("CheckDate")).thenReturn(LocalDate.now());
        participantLogbookView = new ParticipantLogbookView(logController);
    }

    /**
     * Проверяем работу descFoodInit
     */
    @Test
    void testDescFoodInit() throws Exception {
        String str = "String";
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("descFoodInit", String.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, str);

        Field field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        TextArea foodDesc = (TextArea) field.get(participantLogbookView);
        assertEquals("Описание еды", foodDesc.getLabel());
        assertEquals(str, foodDesc.getValue());
        assertEquals(300, foodDesc.getMaxLength());
        assertTrue(foodDesc.isClearButtonVisible());
        assertTrue(foodDesc.isReadOnly());
    }

}
