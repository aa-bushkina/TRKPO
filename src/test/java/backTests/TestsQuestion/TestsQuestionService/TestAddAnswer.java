package backTests.TestsQuestion.TestsQuestionService;


import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionRepository;
import com.cygans.database.question.QuestionService;
import com.cygans.database.question.question_status.QuestionStatus;
import com.cygans.database.question.question_status.QuestionStatusRepository;
import com.cygans.database.question.question_status.StatusOfQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestAddAnswer {
    @Mock
    private Question questionMock;
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionStatusRepository questionStatusRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void testAddAnswer() {
        String answerText = "This is an answer";

        when(questionRepository.getQuestionById(0L)).thenReturn(questionMock);

        QuestionStatus questionStatus = new QuestionStatus(StatusOfQuestion.YES_ANSWER.getText());
        when(questionStatusRepository.findQuestionStatusByStatus(StatusOfQuestion.YES_ANSWER.getText())).thenReturn(questionStatus);

        questionService.addAnswer(0L, answerText);

        verify(questionRepository).getQuestionById(0L);
        Mockito.verify(questionMock).setAnswer(answerText);
        verify(questionMock).setStatusId(questionStatus.getId());
        verify(questionRepository).save(questionMock);
    }
}
