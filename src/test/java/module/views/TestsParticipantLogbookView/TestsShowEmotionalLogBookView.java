package module.views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsShowEmotionalLogBookView {

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
     * Проверяем работу showEmotionalLogBookView когда время не ушло за сутки
     */
    @Test
    void testShowEmotionalLogBookView() throws Exception {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        emotionalLogBook.setDescription("String");
        emotionalLogBook.setTimeType(LocalDateTime.now());
        when(logController.getEmotionalLogByLogbookId(any())).thenReturn(emotionalLogBook);
        when(logController.getAnswerForLog(any())).thenReturn(null);
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("showEmotionalLogBookView");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        Field field = c.getDeclaredField("buttons");
        field.setAccessible(true);
        HorizontalLayout buttons = (HorizontalLayout) field.get(participantLogbookView);
        assertEquals(4, buttons.getChildren().toList().size());
        Field field1 = c.getDeclaredField("formLayout");
        field1.setAccessible(true);
        FormLayout formLayout = (FormLayout) field1.get(participantLogbookView);
        Field field2 = c.getDeclaredField("emotionalDesc");
        field2.setAccessible(true);
        TextArea emotionalDesc = (TextArea) field2.get(participantLogbookView);
        assertEquals(1, formLayout.getResponsiveSteps().size());
        assertEquals(1, formLayout.getChildren().toList().size());
        assertEquals(1, formLayout.getColspan(emotionalDesc));
    }

    /**
     * Проверяем работу showEmotionalLogBookView когда время ушло за сутки
     */
    @Test
    void testShowEmotionalLogBookView_more() throws Exception {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        emotionalLogBook.setDescription("String");
        emotionalLogBook.setTimeType(LocalDateTime.now().minusDays(1));
        when(logController.getEmotionalLogByLogbookId(any())).thenReturn(emotionalLogBook);
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("showEmotionalLogBookView");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        Field field = c.getDeclaredField("buttons");
        field.setAccessible(true);
        HorizontalLayout buttons = (HorizontalLayout) field.get(participantLogbookView);
        assertEquals(3, buttons.getChildren().toList().size());
        Field field1 = c.getDeclaredField("formLayout");
        field1.setAccessible(true);
        FormLayout formLayout = (FormLayout) field1.get(participantLogbookView);
        Field field2 = c.getDeclaredField("emotionalDesc");
        field2.setAccessible(true);
        TextArea emotionalDesc = (TextArea) field2.get(participantLogbookView);
        assertEquals(1, formLayout.getResponsiveSteps().size());
        assertEquals(1, formLayout.getChildren().toList().size());
        assertEquals(1, formLayout.getColspan(emotionalDesc));
    }


}
