package module.views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.combobox.ComboBox;
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
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsAllSetReadOnly {

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
     * allSetReadOnly с EMOTIONAL
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
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, true);
        Field field = c.getDeclaredField("emotionalDesc");
        field.setAccessible(true);
        assertTrue(((TextArea) field.get(participantLogbookView)).isReadOnly());
    }

    /**
     * allSetReadOnly с EATING
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
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, true);
        Field field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        assertTrue(((TextArea) field.get(participantLogbookView)).isReadOnly());
        field = c.getDeclaredField("meal_type");
        field.setAccessible(true);
        assertTrue(((ComboBox<String>) field.get(participantLogbookView)).isReadOnly());
        field = c.getDeclaredField("hourFood");
        field.setAccessible(true);
        assertTrue(((ComboBox<String>) field.get(participantLogbookView)).isReadOnly());
        field = c.getDeclaredField("minuteFood");
        field.setAccessible(true);
        assertTrue(((ComboBox<String>) field.get(participantLogbookView)).isReadOnly());
    }

    /**
     * allSetReadOnly с SPORT
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
        Method method = c.getDeclaredMethod("allSetReadOnly", boolean.class);
        method.setAccessible(true);
        method.invoke(participantLogbookView, true);
        Field field = c.getDeclaredField("sportDesc");
        field.setAccessible(true);
        assertTrue(((TextArea) field.get(participantLogbookView)).isReadOnly());
        field = c.getDeclaredField("activityField");
        field.setAccessible(true);
        assertTrue(((TextField) field.get(participantLogbookView)).isReadOnly());
        field = c.getDeclaredField("durationField");
        field.setAccessible(true);
        assertTrue(((TextField) field.get(participantLogbookView)).isReadOnly());
        field = c.getDeclaredField("intensity_type");
        field.setAccessible(true);
        assertTrue(((ComboBox<String>) field.get(participantLogbookView)).isReadOnly());
    }

}
