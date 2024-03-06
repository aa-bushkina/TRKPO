package module.views.TestsMentorParticipantsLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.mentor.logbooks.MentorParticipantsLogbookView;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsHourFoodTextInit {

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
     * Проверяем работу hourFoodTextInit
     */
    @Test
    void testHourFoodTextInit() throws Exception {
        String str = "String";
        Class c = MentorParticipantsLogbookView.class;
        Method method = c.getDeclaredMethod("hourFoodTextInit", String.class);
        method.setAccessible(true);
        method.invoke(mentorParticipantsLogbookView, str);

        Field field = c.getDeclaredField("hourFood");
        field.setAccessible(true);
        TextField hourFood = (TextField) field.get(mentorParticipantsLogbookView);
        assertEquals("Час прием пищи", hourFood.getLabel());
        assertEquals(str, hourFood.getValue());
        assertEquals(2, hourFood.getMaxLength());
        assertTrue(hourFood.isClearButtonVisible());
        assertTrue(hourFood.isReadOnly());
    }

}
