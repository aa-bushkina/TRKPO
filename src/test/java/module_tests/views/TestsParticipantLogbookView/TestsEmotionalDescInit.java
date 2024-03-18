package module_tests.views.TestsParticipantLogbookView;
import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
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
public class TestsEmotionalDescInit {

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
     * Проверяем работу emotionalDescInit
     */
    @Test
    void testEmotionalDescInit() throws Exception {
        String str = "String";
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("emotionalDescInit", String.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, str);
        Field field = c.getDeclaredField("emotionalDesc");
        field.setAccessible(true);
        TextArea emotionalDesc = (TextArea) field.get(participantLogbookView);
        assertEquals("Описание", emotionalDesc.getLabel());
        assertEquals(str, emotionalDesc.getValue());
        assertEquals(1000, emotionalDesc.getMaxLength());
        assertTrue(emotionalDesc.isClearButtonVisible());
        assertTrue(emotionalDesc.isReadOnly());
    }
}