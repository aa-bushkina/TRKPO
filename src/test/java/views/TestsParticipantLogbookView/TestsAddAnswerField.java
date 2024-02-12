package views.TestsParticipantLogbookView;


import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.button.Button;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsAddAnswerField {

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
     * Проверяем работу addAnswerField
     */
    @Test
    void testAddAnswerField() throws Exception {
        String str = "String";
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        method = c.getDeclaredMethod("addAnswerField", String.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, str);
        Field field = c.getDeclaredField("answerField");
        field.setAccessible(true);
        TextArea answerField = (TextArea) field.get(participantLogbookView);
        assertEquals("Ответ ментора", answerField.getLabel());
        assertEquals(str, answerField.getValue());
        assertEquals(1000, answerField.getMaxLength());
        assertTrue(answerField.isClearButtonVisible());
        assertTrue(answerField.isReadOnly());
    }

}
