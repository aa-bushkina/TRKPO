package module.views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.button.Button;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsChangeLogInit {

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
     * Проверяем работу changeLogInit
     */
    @Test
    void testChangeLogInit() throws Exception {
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("changeLogInit");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        Field field = c.getDeclaredField("changeLog");
        field.setAccessible(true);
        Button changeLog = (Button) field.get(participantLogbookView);
        assertEquals("Редактировать", changeLog.getText());
        assertEquals("auto", changeLog.getElement().getStyle().get("margin-left"));
        assertEquals("primary", changeLog.getThemeName());
        changeLog.click();

        field = c.getDeclaredField("back");
        field.setAccessible(true);
        Button back_button = (Button) field.get(participantLogbookView);
        assertFalse(back_button.isVisible());
        assertFalse(changeLog.isVisible());
        field = c.getDeclaredField("save");
        field.setAccessible(true);
        Button save = (Button) field.get(participantLogbookView);
        field = c.getDeclaredField("cancel");
        field.setAccessible(true);
        Button cancel = (Button) field.get(participantLogbookView);
        assertTrue(save.isVisible());
        assertTrue(cancel.isVisible());
    }


}
