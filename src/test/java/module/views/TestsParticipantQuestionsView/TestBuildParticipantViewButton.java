package module.views.TestsParticipantQuestionsView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.views.participant.questions.ParticipantQuestionsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestBuildParticipantViewButton {

    @Mock
    private QuestionController questionController;

    @Mock
    private ParticipantAndMentorController participantAndMentorController;

    @Mock
    private NotificationController notificationController;

    @Mock
    private VaadinSession vaadinSession;

    private ParticipantQuestionsView participantQuestionsView;

    /**
     * Проверка buildParticipantViewButton
     */
    @Test
    void testsBuildParticipantViewButton() throws Exception {
        VaadinSession.setCurrent(vaadinSession);
        participantQuestionsView = new ParticipantQuestionsView(questionController, participantAndMentorController, notificationController);
        Question question = new Question();
        Class c = ParticipantQuestionsView.class;
        Method method = c.getDeclaredMethod("buildParticipantViewButton", Question.class);
        method.setAccessible(true);
        Button button = (Button) method.invoke(participantQuestionsView, question);
        assertEquals("Смотреть", button.getText());
        assertEquals("primary", button.getThemeName());
        button.click();
    }

}
