package module.views.TestsMentorParticipantsLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.views.mentor.logbooks.MentorParticipantsLogbookView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @Mock
    private LogController logController;

    @Mock
    private VaadinSession vaadinSession;

    private MentorParticipantsLogbookView mentorParticipantsLogbookView;

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
        mentorParticipantsLogbookView = new MentorParticipantsLogbookView(logController);
        Class c = MentorParticipantsLogbookView.class;
        Field field = c.getDeclaredField("logBookType");
        field.setAccessible(true);
        assertEquals("LogbookType",  (String) field.get(mentorParticipantsLogbookView));
        field = c.getDeclaredField("logBookId");
        field.setAccessible(true);
        assertEquals(1L,  (Long) field.get(mentorParticipantsLogbookView));
        field = c.getDeclaredField("selectDate");
        field.setAccessible(true);
        assertEquals(LocalDate.now(),  (LocalDate) field.get(mentorParticipantsLogbookView));
        field = c.getDeclaredField("mainLayout");
        field.setAccessible(true);
        VerticalLayout mainLayout = (VerticalLayout) field.get(mentorParticipantsLogbookView);
        assertEquals(4, mainLayout.getChildren().toList().size());
        assertEquals("600px", mainLayout.getMaxWidth());
        assertFalse(mainLayout.isPadding());
    }

    /**
     * Лог с ответом
     */
    @Test
    void general_answer() throws Exception {
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("LogbookType");
        when(logController.getAnswerForLog(any())).thenReturn("Answer");
        mentorParticipantsLogbookView = new MentorParticipantsLogbookView(logController);
        Class c = MentorParticipantsLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(mentorParticipantsLogbookView);
        assertEquals(1, formLayout.getChildren().toList().size());
    }


    /**
     * Лог с EMOTIONAL
     */
    @Test
    void general_emotional() throws Exception {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        emotionalLogBook.setDescription("String");
        when(logController.getEmotionalLogByLogbookId(any())).thenReturn(emotionalLogBook);
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Эмоциональное состояние");
        mentorParticipantsLogbookView = new MentorParticipantsLogbookView(logController);
        Class c = MentorParticipantsLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(mentorParticipantsLogbookView);
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
        when(logController.getEatingLogByLogbookId(any())).thenReturn(eatingLogBook);
        when(logController.getMealEatingLog(any())).thenReturn("Type");
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Приём пищи");
        mentorParticipantsLogbookView = new MentorParticipantsLogbookView(logController);
        Class c = MentorParticipantsLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(mentorParticipantsLogbookView);
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
        Intensity intensity = new Intensity();
        intensity.setType("Type");
        when(logController.getSportLogByLogbookId(any())).thenReturn(sportLogBook);
        when(logController.getIntensitySportLog(any())).thenReturn(intensity);
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Спортивная активность");
        mentorParticipantsLogbookView = new MentorParticipantsLogbookView(logController);
        Class c = MentorParticipantsLogbookView.class;
        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(mentorParticipantsLogbookView);
        assertEquals(4, formLayout.getChildren().toList().size());
    }

}
