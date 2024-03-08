package module.views.TestsMentorParticipantsLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.mentor.logbooks.MentorParticipantsLogbookView;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsDescFoodInit {

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
     * Проверяем работу descFoodInit
     */
    @Test
    void testDescFoodInit() throws Exception {
        String str = "String";
        Class c = MentorParticipantsLogbookView.class;
        Method method = c.getDeclaredMethod("descFoodInit", String.class);
        method.setAccessible(true);
        method.invoke(mentorParticipantsLogbookView, str);

        Field field = c.getDeclaredField("foodDesc");
        field.setAccessible(true);
        TextArea foodDesc = (TextArea) field.get(mentorParticipantsLogbookView);
        assertEquals("Описание еды", foodDesc.getLabel());
        assertEquals(str, foodDesc.getValue());
        assertTrue(foodDesc.isClearButtonVisible());
        assertTrue(foodDesc.isReadOnly());
    }


}