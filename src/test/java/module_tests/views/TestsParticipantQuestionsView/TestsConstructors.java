package module_tests.views.TestsParticipantQuestionsView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.views.participant.questions.ParticipantQuestionsView;
import com.vaadin.flow.component.textfield.TextArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsConstructors {

    @Mock
    private QuestionController questionController;

    @Mock
    private ParticipantAndMentorController participantAndMentorController;

    @Mock
    private NotificationController notificationController;

    private ParticipantQuestionsView participantQuestionsView;

    /**
     * Проверяем общие настройки констурктора
     */
    @Test
    void testConstructor() throws Exception {
        participantQuestionsView = new ParticipantQuestionsView(questionController, participantAndMentorController, notificationController);
        Class c = ParticipantQuestionsView.class;
        Field field = c.getDeclaredField("textArea");
        field.setAccessible(true);
        TextArea textArea = (TextArea) field.get(participantQuestionsView);
        assertEquals("50%", textArea.getWidth());
        assertEquals("Например: я отжался 10 раз - можно ли съесть пиццу?", textArea.getPlaceholder());
        assertEquals(300, textArea.getMaxLength());
        assertEquals(1, textArea.getMinLength());
        assertEquals("200px", textArea.getHeight());
        assertTrue(textArea.isClearButtonVisible());
    }


}
