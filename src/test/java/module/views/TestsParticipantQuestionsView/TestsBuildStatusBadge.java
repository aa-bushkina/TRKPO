package module.views.TestsParticipantQuestionsView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.views.participant.questions.ParticipantQuestionsView;
import com.vaadin.flow.component.html.Span;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsBuildStatusBadge {


    @Mock
    private QuestionController questionController;

    @Mock
    private ParticipantAndMentorController participantAndMentorController;

    @Mock
    private NotificationController notificationController;

    private ParticipantQuestionsView participantQuestionsView;

    /**
     * Проверка buildStatusBadge c StatusOfQuestion.NO_ANSWER
     */
    @Test
    void testsBuildStatusBadge() throws Exception {
        participantQuestionsView = new ParticipantQuestionsView(questionController, participantAndMentorController, notificationController);
        Question question = new Question();
        when(questionController.getQuestionStatus(question)).thenReturn(StatusOfQuestion.NO_ANSWER.getText());
        Class c = ParticipantQuestionsView.class;
        Method method = c.getDeclaredMethod("buildStatusBadge", Question.class);
        method.setAccessible(true);
        Span statusBadge = (Span) method.invoke(participantQuestionsView, question);
        assertEquals("badge", statusBadge.getElement().getThemeList().stream().toList().get(0));
        assertEquals("error", statusBadge.getElement().getThemeList().stream().toList().get(1));
    }

    /**
     * Проверка buildStatusBadge не с StatusOfQuestion.NO_ANSWER
     */
    @Test
    void testsBuildStatusBadge_not() throws Exception {
        participantQuestionsView = new ParticipantQuestionsView(questionController, participantAndMentorController, notificationController);
        Question question = new Question();
        when(questionController.getQuestionStatus(question)).thenReturn(StatusOfQuestion.YES_ANSWER.getText());
        Class c = ParticipantQuestionsView.class;
        Method method = c.getDeclaredMethod("buildStatusBadge", Question.class);
        method.setAccessible(true);
        Span statusBadge = (Span) method.invoke(participantQuestionsView, question);
        assertEquals("badge", statusBadge.getElement().getThemeList().stream().toList().get(0));
        assertEquals("success", statusBadge.getElement().getThemeList().stream().toList().get(1));
    }

}
