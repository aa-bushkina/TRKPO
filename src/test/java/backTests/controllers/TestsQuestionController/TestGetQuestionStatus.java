package backTests.controllers.TestsQuestionController;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.QuestionStatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGetQuestionStatus {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionStatusService questionStatusService;

    @Test
    void testGetQuestionStatus() {
        Long statusId = 1L;
        String expectedStatus = "Open";
        Question question = new Question();
        question.setStatusId(statusId);

        when(questionStatusService.getQuestionType(statusId)).thenReturn(expectedStatus);
        String actualStatus = questionController.getQuestionStatus(question);
        verify(questionStatusService).getQuestionType(statusId);

        assertEquals(expectedStatus, actualStatus);
    }
}
