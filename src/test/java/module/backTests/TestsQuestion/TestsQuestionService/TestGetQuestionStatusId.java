package module.backTests.TestsQuestion.TestsQuestionService;


import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionRepository;
import com.cygans.database.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestGetQuestionStatusId {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService service;

    @Test
    public void testGetQuestionByIdWhenQuestionExists() {
        Question question = new Question();
        when(questionRepository.getQuestionById(0L)).thenReturn(question);

        Question actualQuestion = service.getQuestionById(0L);

        assertEquals(question, actualQuestion);
    }

    @Test
    public void testGetQuestionByIdWhenQuestionDoesNotExist() {
        Long nonExistentId = 999L;
        when(questionRepository.getQuestionById(nonExistentId)).thenReturn(null);
        Question actualQuestion = service.getQuestionById(nonExistentId);

        assertNull(actualQuestion);
    }
}
