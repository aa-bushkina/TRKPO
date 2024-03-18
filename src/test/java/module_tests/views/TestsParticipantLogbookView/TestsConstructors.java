package module_tests.views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConstructors {

    @Mock
    private LogController logController;

    @Mock
    private VaadinSession vaadinSession;

    private ParticipantLogbookView participantLogbookView;

    @BeforeEach
    void before() {
        VaadinSession.setCurrent(vaadinSession);
        when(vaadinSession.getAttribute("LogbookId")).thenReturn(1L);
        when(vaadinSession.getAttribute("CheckDate")).thenReturn(LocalDate.now());
    }

    /**
     * Общие настройки в конструкторе
     */
    @Test
    void general() throws Exception {
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("LogbookType");
        participantLogbookView = new ParticipantLogbookView(logController);
        Class c = ParticipantLogbookView.class;
        Field field = c.getDeclaredField("logBookType");
        field.setAccessible(true);
        assertEquals("LogbookType",  (String) field.get(participantLogbookView));
        field = c.getDeclaredField("logBookId");
        field.setAccessible(true);
        assertEquals(1L,  (Long) field.get(participantLogbookView));
        field = c.getDeclaredField("selectDate");
        field.setAccessible(true);
        assertEquals(LocalDate.now(),  (LocalDate) field.get(participantLogbookView));
        field = c.getDeclaredField("mainLayout");
        field.setAccessible(true);
        VerticalLayout mainLayout = (VerticalLayout) field.get(participantLogbookView);
        assertEquals(4, mainLayout.getChildren().toList().size());
        assertEquals("600px", mainLayout.getMaxWidth());
        assertFalse(mainLayout.isPadding());
        field = c.getDeclaredField("buttons");
        field.setAccessible(true);
        HorizontalLayout buttons = (HorizontalLayout) field.get(participantLogbookView);
        assertEquals(buttons.getWidth(), mainLayout.getWidth());
    }

    /**
     * Лог с ответом
     */
    @Test
    void general_answer() throws Exception {
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("LogbookType");
        when(logController.getAnswerForLog(any())).thenReturn("Answer");
        participantLogbookView = new ParticipantLogbookView(logController);
        Class c = ParticipantLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(participantLogbookView);
        assertEquals(1, formLayout.getChildren().toList().size());
    }


    /**
     * Лог с EMOTIONAL
     */
    @Test
    void general_emotional() throws Exception {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        emotionalLogBook.setDescription("String");
        emotionalLogBook.setTimeType(LocalDateTime.now());
        when(logController.getEmotionalLogByLogbookId(any())).thenReturn(emotionalLogBook);
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Эмоциональное состояние");
        participantLogbookView = new ParticipantLogbookView(logController);
        Class c = ParticipantLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(participantLogbookView);
        assertEquals(1, formLayout.getChildren().toList().size());
    }

    /**
     * Лог с EATING
     */
    @Test
    void general_eating() throws Exception {
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setMealId(1L);
        eatingLogBook.setDescription("Description");
        eatingLogBook.setTimeEat(LocalTime.now());
        eatingLogBook.setTimeType(LocalDateTime.now());
        when(logController.getEatingLogByLogbookId(any())).thenReturn(eatingLogBook);
        when(logController.getMealEatingLog(any())).thenReturn("Type");
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Приём пищи");
        participantLogbookView = new ParticipantLogbookView(logController);
        Class c = ParticipantLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(participantLogbookView);
        assertEquals(4, formLayout.getChildren().toList().size());
    }

    /**
     * Лог с SPORT
     */
    @Test
    void general_sport() throws Exception {
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
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Спортивная активность");
        participantLogbookView = new ParticipantLogbookView(logController);
        Class c = ParticipantLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(participantLogbookView);
        assertEquals(4, formLayout.getChildren().toList().size());
    }

}
