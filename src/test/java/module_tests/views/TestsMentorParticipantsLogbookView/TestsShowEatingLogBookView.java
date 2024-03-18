package module_tests.views.TestsMentorParticipantsLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.views.mentor.logbooks.MentorParticipantsLogbookView;
import com.vaadin.flow.component.formlayout.FormLayout;
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

    private MentorParticipantsLogbookView mentorParticipantsLogbookView;

    @BeforeEach
    void before() {
        VaadinSession.setCurrent(vaadinSession);
        when(vaadinSession.getAttribute("LogbookType")).thenReturn("LogbookType");
        when(vaadinSession.getAttribute("LogbookId")).thenReturn(1L);
        when(vaadinSession.getAttribute("CheckDate")).thenReturn(LocalDate.now());
        mentorParticipantsLogbookView = new MentorParticipantsLogbookView(logController);
    }

    /**
     * Проверяем работу showEatingLogBookView
     */
    @Test
    void testsShowEatingLogBookView() throws Exception {
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setMealId(1L);
        eatingLogBook.setDescription("Description");
        eatingLogBook.setTimeEat(LocalTime.now());
        when(logController.getEatingLogByLogbookId(any())).thenReturn(eatingLogBook);
        when(logController.getMealEatingLog(any())).thenReturn("Type");
        Class c = MentorParticipantsLogbookView.class;
        Method method = c.getDeclaredMethod("showEatingLogBookView");
        method.setAccessible(true);
        method.invoke(mentorParticipantsLogbookView);

        Field field = c.getDeclaredField("formLayout");
        field.setAccessible(true);
        FormLayout formLayout = (FormLayout) field.get(mentorParticipantsLogbookView);
        assertEquals(4, formLayout.getChildren().toList().size());
        assertEquals(1, formLayout.getResponsiveSteps().size());

        field = c.getDeclaredField("meal_type");
        field.setAccessible(true);
        TextField meal_type = (TextField) field.get(mentorParticipantsLogbookView);
        assertEquals(1, formLayout.getColspan(meal_type));
        field = c.getDeclaredField("hourFood");
        field.setAccessible(true);
        TextField hourFood = (TextField) field.get(mentorParticipantsLogbookView);
        assertEquals(1, formLayout.getColspan(hourFood));
        field = c.getDeclaredField("minuteFood");
        field.setAccessible(true);
        TextField minuteFood = (TextField) field.get(mentorParticipantsLogbookView);
        assertEquals(1, formLayout.getColspan(minuteFood));
        field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        TextArea foodDesc = (TextArea) field.get(mentorParticipantsLogbookView);
        assertEquals(1, formLayout.getColspan(foodDesc));
    }

}
