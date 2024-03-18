package module_tests.backTests.TestsQuestion.TestsQuestionService;


import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionRepository;
import com.cygans.database.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestSaveQuestion {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService service;

    @Test
    public void testSaveQuestion() {
        Question question = new Question();

        service.saveQuestion(question);

        verify(questionRepository).save(question);
    }
}
