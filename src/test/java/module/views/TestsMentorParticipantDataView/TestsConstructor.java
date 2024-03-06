package module.views.TestsMentorParticipantDataView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @Mock
    private LogController logController;

    private MentorParticipantDataView mentorParticipantDataView;

    /**
     * Проверка конструктора
     */
    @Test
    void general() throws Exception {
        when(logController.getParticipantFullNameByAttribute()).thenReturn("Михаил Иванов");
        mentorParticipantDataView = new MentorParticipantDataView(logController);
        Class c = MentorParticipantDataView.class;
        Field field = c.getDeclaredField("download");
        field.setAccessible(true);
        Icon downloadIcon = (Icon) field.get(mentorParticipantDataView);
        assertEquals("50px", downloadIcon.getStyle().get("width"));
        assertEquals("50px", downloadIcon.getStyle().get("height"));
        field = c.getDeclaredField("downloadBut");
        field.setAccessible(true);
        Button downloadButton = (Button) field.get(mentorParticipantDataView);
        assertEquals("60px", downloadButton.getHeight());
        assertEquals("60px", downloadButton.getWidth());
        downloadButton.click();
    }

}
