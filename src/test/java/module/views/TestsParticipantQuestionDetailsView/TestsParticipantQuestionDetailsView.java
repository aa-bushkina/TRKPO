package module.views.TestsParticipantQuestionDetailsView;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.views.participant.questions.ParticipantQuestionDetailsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsParticipantQuestionDetailsView {

    @Mock
    private VaadinSession vaadinSession;

    @Mock
    private QuestionController questionControllerMock;

    @Mock
    private UI ui;

    private ParticipantQuestionDetailsView participantQuestionDetailsView;

    @BeforeEach
    public void setUp() {
        Question question = new Question();
        question.setQuestion("Question");
        vaadinSession.setAttribute("CheckDate", LocalDate.of(2022, 10, 20));
        VaadinSession.setCurrent(vaadinSession);
        when(questionControllerMock.getNowQuestionInSession()).thenReturn(question);
        when(vaadinSession.getAttribute("CheckDate")).thenReturn(LocalDate.of(2022, 10, 20));
        participantQuestionDetailsView = new ParticipantQuestionDetailsView(questionControllerMock);
    }

    /**
     * Проверка метода setStyles() при наличии вопроса без ответа.
     */
    @Test
    public void testSetStyles_QuestionWithoutAnswer() throws Exception {
        Question question = new Question();
        question.setQuestion("Test question");
        when(questionControllerMock.getNowQuestionInSession()).thenReturn(question);
        Class c = ParticipantQuestionDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantQuestionDetailsView);
        Field field = c.getDeclaredField("questionText");
        field.setAccessible(true);
        Field field2 = c.getDeclaredField("answerText");
        field2.setAccessible(true);
        assertFalse(((TextArea) field2.get(participantQuestionDetailsView)).isVisible());
        assertEquals("Test question", ((TextArea) field.get(participantQuestionDetailsView)).getValue());
    }

    /**
     * Проверка метода setStyles() при наличии вопроса с ответом.
     */
    @Test
    public void testSetStyles_QuestionWithAnswer() throws Exception {
        Question question = new Question();
        question.setQuestion("Test question");
        question.setAnswer("Test answer");
        when(questionControllerMock.getNowQuestionInSession()).thenReturn(question);
        Class c = ParticipantQuestionDetailsView.class;
        Method method = c.getDeclaredMethod("setStyles");
        method.setAccessible(true);
        method.invoke(participantQuestionDetailsView);
        Field field = c.getDeclaredField("questionText");
        field.setAccessible(true);
        Field field2 = c.getDeclaredField("answerText");
        field2.setAccessible(true);
        assertTrue(((TextArea) field2.get(participantQuestionDetailsView)).isVisible());
        assertEquals("Test question", ((TextArea) field.get(participantQuestionDetailsView)).getValue());
        assertEquals("Test answer", ((TextArea) field2.get(participantQuestionDetailsView)).getValue());
    }

    /**
     * Проверка обработки события нажатия на кнопку "Назад".
     */
    @Test
    public void testBackButtonClick() throws Exception {
        Class c = ParticipantQuestionDetailsView.class;
        Field field = c.getDeclaredField("backBtn");
        field.setAccessible(true);
        assertNotNull(field.get(participantQuestionDetailsView));
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) field.get(participantQuestionDetailsView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        verify(questionControllerMock).getNowQuestionInSession();
    }

}
