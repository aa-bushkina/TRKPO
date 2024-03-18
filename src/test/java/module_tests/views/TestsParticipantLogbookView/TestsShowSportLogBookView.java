package module_tests.views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsShowSportLogBookView {

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
     * Проверяем работу showSportLogBookView, когда время не вышло за сутки
     */
    @Test
    void testShowSportLogBookView() throws Exception {
        SportLogBook sportLogBook = new SportLogBook();
        sportLogBook.setIntensityId(1L);
        sportLogBook.setActivity("Activity");
        sportLogBook.setComments("Comments");
        sportLogBook.setDuration(1);
        sportLogBook.setTimeType(LocalDateTime.now());
        Intensity intensity = new Intensity();
        intensity.setType("Type");
        when(logController.getSportLogByLogbookId(any())).thenReturn(sportLogBook);
        when(logController.getIntensitySportLog(any())).thenReturn(intensity);
        when(logController.getAnswerForLog(any())).thenReturn(null);
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("showSportLogBookView");
        method.setAccessible(true);
        method.invoke(participantLogbookView);

        Field field = c.getDeclaredField("buttons");
        field.setAccessible(true);
        HorizontalLayout buttons = (HorizontalLayout) field.get(participantLogbookView);
        assertEquals(4, buttons.getChildren().toList().size());

        field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(participantLogbookView);
        assertEquals(4, formLayout.getChildren().toList().size());
        assertEquals(1, formLayout.getResponsiveSteps().size());

        field = c.getDeclaredField("intensity_type");
        field.setAccessible(true);
        ComboBox<String> intensity_type = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(intensity_type));
        field = c.getDeclaredField("activityField");
        field.setAccessible(true);
        TextField activityField = (TextField) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(activityField));
        field = c.getDeclaredField("sportDesc");
        field.setAccessible(true);
        TextArea sportDesc = (TextArea) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(sportDesc));
        field = c.getDeclaredField("durationField");
        field.setAccessible(true);
        TextField durationField = (TextField) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(durationField));
    }

    /**
     * Проверяем работу showSportLogBookView, когда время вышло за сутки
     */
    @Test
    void testShowSportLogBookView_more() throws Exception {
        SportLogBook sportLogBook = new SportLogBook();
        sportLogBook.setIntensityId(1L);
        sportLogBook.setActivity("Activity");
        sportLogBook.setComments("Comments");
        sportLogBook.setDuration(1);
        sportLogBook.setTimeType(LocalDateTime.now().minusDays(1));
        Intensity intensity = new Intensity();
        intensity.setType("Type");
        when(logController.getSportLogByLogbookId(any())).thenReturn(sportLogBook);
        when(logController.getIntensitySportLog(any())).thenReturn(intensity);
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("showSportLogBookView");
        method.setAccessible(true);
        method.invoke(participantLogbookView);

        Field field = c.getDeclaredField("buttons");
        field.setAccessible(true);
        HorizontalLayout buttons = (HorizontalLayout) field.get(participantLogbookView);
        assertEquals(3, buttons.getChildren().toList().size());

        field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(participantLogbookView);
        assertEquals(4, formLayout.getChildren().toList().size());
        assertEquals(1, formLayout.getResponsiveSteps().size());

        field = c.getDeclaredField("intensity_type");
        field.setAccessible(true);
        ComboBox<String> intensity_type = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(intensity_type));
        field = c.getDeclaredField("activityField");
        field.setAccessible(true);
        TextField activityField = (TextField) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(activityField));
        field = c.getDeclaredField("sportDesc");
        field.setAccessible(true);
        TextArea sportDesc = (TextArea) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(sportDesc));
        field = c.getDeclaredField("durationField");
        field.setAccessible(true);
        TextField durationField = (TextField) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(durationField));
    }

}
