package module_tests.views.TestsParticipantLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.vaadin.flow.component.combobox.ComboBox;
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
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsShowEatingLogBookView {

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
     * Проверяем работу showEatingLogBookView, когда время не вышло за сутки
     */
    @Test
    void testsShowEatingLogBookView() throws Exception {
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setMealId(1L);
        eatingLogBook.setDescription("Description");
        eatingLogBook.setTimeEat(LocalTime.now());
        eatingLogBook.setTimeType(LocalDateTime.now());
        when(logController.getEatingLogByLogbookId(any())).thenReturn(eatingLogBook);
        when(logController.getMealEatingLog(any())).thenReturn("Type");
        when(logController.getAnswerForLog(any())).thenReturn(null);
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("showEatingLogBookView");
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

        field = c.getDeclaredField("meal_type");
        field.setAccessible(true);
        ComboBox<String> meal_type = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(meal_type));
        field = c.getDeclaredField("hourFood");
        field.setAccessible(true);
        ComboBox<String> hourFood = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(hourFood));
        field = c.getDeclaredField("minuteFood");
        field.setAccessible(true);
        ComboBox<String> minuteFood = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(minuteFood));
        field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        TextArea foodDesc = (TextArea) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(foodDesc));
    }

    /**
     * Проверяем работу showEatingLogBookView, когда время вышло за сутки
     */
    @Test
    void testsShowEatingLogBookView_more() throws Exception {
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setMealId(1L);
        eatingLogBook.setDescription("Description");
        eatingLogBook.setTimeEat(LocalTime.now());
        eatingLogBook.setTimeType(LocalDateTime.now().minusDays(1));
        when(logController.getEatingLogByLogbookId(any())).thenReturn(eatingLogBook);
        when(logController.getMealEatingLog(any())).thenReturn("Type");
        Class c = ParticipantLogbookView.class;
        Method method = c.getDeclaredMethod("showEatingLogBookView");
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

        field = c.getDeclaredField("meal_type");
        field.setAccessible(true);
        ComboBox<String> meal_type = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(meal_type));
        field = c.getDeclaredField("hourFood");
        field.setAccessible(true);
        ComboBox<String> hourFood = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(hourFood));
        field = c.getDeclaredField("minuteFood");
        field.setAccessible(true);
        ComboBox<String> minuteFood = (ComboBox<String>) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(minuteFood));
        field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        TextArea foodDesc = (TextArea) field.get(participantLogbookView);
        assertEquals(1, formLayout.getColspan(foodDesc));
    }

}
