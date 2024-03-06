package module.backTests.controllers.TestsQuestionController;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetNowQuestionInSession {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);
    }

    @Test
    void testGetNowQuestionInSession() {
        Long questionId = 1L;
        Question expectedQuestion = new Question(); // Создаем ожидаемый возвращаемый объект
        when(questionService.getQuestionById(questionId)).thenReturn(expectedQuestion);

        when(VaadinSession.getCurrent().getAttribute("QuestionId")).thenReturn(questionId);

        Question actualQuestion = questionController.getNowQuestionInSession();

        verify(questionService).getQuestionById(questionId);

        assertEquals(expectedQuestion, actualQuestion);
    }
}

