package views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsCancelInit {

    @Mock
    private LogController logController;

    @Mock
    private VaadinSession vaadinSession;

    @Mock
    private  UI ui;

    private ParticipantLogbookView participantLogbookView;

    @BeforeEach
    void before() {
        VaadinSession.setCurrent(vaadinSession);
        when(vaadinSession.getAttribute("LogbookId")).thenReturn(1L);
        when(vaadinSession.getAttribute("CheckDate")).thenReturn(LocalDate.now());
    }


    /**
     * Проверяем работу cancelInit с EMOTIONAL
     */
    @Test
    void cancelInit_emotional() throws Exception {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        emotionalLogBook.setDescription("String");
        emotionalLogBook.setTimeType(LocalDateTime.now());
        when(logController.getEmotionalLogByLogbookId(any())).thenReturn(emotionalLogBook);
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("Эмоциональное состояние");
        participantLogbookView = new ParticipantLogbookView(logController);
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("cancelInit");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        Field field = c.getDeclaredField("cancel");
        field.setAccessible(true);
        Button cancel = (Button) field.get(participantLogbookView);
        assertEquals("Отменить", cancel.getText());
        assertEquals("auto", cancel.getElement().getStyle().get("margin-right"));
        assertFalse(cancel.isVisible());
        UI.setCurrent(ui);
        UI.getCurrent().access(cancel::click);
        field = c.getDeclaredField("emotionalDesc");
        field.setAccessible(true);
        Field field1 = c.getDeclaredField("emotionalDescText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((TextArea) field.get(participantLogbookView)).getValue());
    }

    /**
     * Проверяем работу cancelInit с EATING
     */
    @Test
    void cancelInit_eating() throws Exception {
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
        Method method = c.getDeclaredMethod("cancelInit");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        Field field = c.getDeclaredField("cancel");
        field.setAccessible(true);
        Button cancel = (Button) field.get(participantLogbookView);
        assertEquals("Отменить", cancel.getText());
        assertEquals("auto", cancel.getElement().getStyle().get("margin-right"));
        assertFalse(cancel.isVisible());
        UI.setCurrent(ui);
        UI.getCurrent().access(cancel::click);

        field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        Field field1 = c.getDeclaredField("foodDescText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((TextArea) field.get(participantLogbookView)).getValue());

        field = c.getDeclaredField("hourFood");
        field.setAccessible(true);
        field1 = c.getDeclaredField("hourFoodText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((ComboBox<String>) field.get(participantLogbookView)).getValue());

        field = c.getDeclaredField("minuteFood");
        field.setAccessible(true);
        field1 = c.getDeclaredField("minuteFoodText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((ComboBox<String>) field.get(participantLogbookView)).getValue());

        field = c.getDeclaredField("meal_type");
        field.setAccessible(true);
        field1 = c.getDeclaredField("mealTypeText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((ComboBox<String>) field.get(participantLogbookView)).getValue());
    }

    /**
     * Проверяем работу cancelInit с SPORT
     */
    @Test
    void cancelInit_sport() throws Exception {
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
        Method method = c.getDeclaredMethod("cancelInit");
        method.setAccessible(true);
        method.invoke(participantLogbookView);
        Field field = c.getDeclaredField("cancel");
        field.setAccessible(true);
        Button cancel = (Button) field.get(participantLogbookView);
        assertEquals("Отменить", cancel.getText());
        assertEquals("auto", cancel.getElement().getStyle().get("margin-right"));
        assertFalse(cancel.isVisible());
        UI.setCurrent(ui);
        UI.getCurrent().access(cancel::click);

        field = c.getDeclaredField("activityField");
        field.setAccessible(true);
        Field field1 = c.getDeclaredField("activityText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((TextField) field.get(participantLogbookView)).getValue());

        field = c.getDeclaredField("durationField");
        field.setAccessible(true);
        field1 = c.getDeclaredField("durationText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((TextField) field.get(participantLogbookView)).getValue());

        field = c.getDeclaredField("sportDesc");
        field.setAccessible(true);
        field1 = c.getDeclaredField("sportDescText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((TextArea) field.get(participantLogbookView)).getValue());

        field = c.getDeclaredField("intensity_type");
        field.setAccessible(true);
        field1 = c.getDeclaredField("intensityTypeText");
        field1.setAccessible(true);
        assertEquals((String) field1.get(participantLogbookView), ((ComboBox<String>) field.get(participantLogbookView)).getValue());
    }

}
