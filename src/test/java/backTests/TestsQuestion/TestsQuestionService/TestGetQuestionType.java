package backTests.TestsQuestion.TestsQuestionService;


import com.cygans.database.question.question_status.QuestionStatus;
import com.cygans.database.question.question_status.QuestionStatusRepository;
import com.cygans.database.question.question_status.QuestionStatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestGetQuestionType {

    @Mock
    private QuestionStatusRepository questionStatusRepository;

    @InjectMocks
    private QuestionStatusService service;

    @Test
    public void testGetQuestionTypeWhenIdExists() {
        Long id = 1L;
        String expectedType = "Нет ответа";
        QuestionStatus questionStatus = new QuestionStatus(expectedType);
        when(questionStatusRepository.findQuestionStatusById(id)).thenReturn(questionStatus);

        String actualType = service.getQuestionType(id);

        assertEquals(expectedType, actualType);
    }
}
