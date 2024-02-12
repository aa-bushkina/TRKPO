package views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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
public class TestsDurationInit {
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
     * Проверяем работу durationInit
     */
    @Test
    void testDurationInit() throws Exception {
        String str = "String";
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("durationInit", String.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, str);
        Field field = c.getDeclaredField("durationField");
        field.setAccessible(true);
        TextField durationField = (TextField) field.get(participantLogbookView);
        assertEquals("Продолжительность (в минутах)", durationField.getLabel());
        assertEquals(str, durationField.getValue());
        assertEquals(4, durationField.getMaxLength());
        assertTrue(durationField.isClearButtonVisible());
        assertTrue(durationField.isReadOnly());
    }

}
