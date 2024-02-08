package backTests.TestsQuestion.TestsQuestionService;

import com.cygans.database.question.question_status.QuestionStatus;
import com.cygans.database.question.question_status.QuestionStatusRepository;
import com.cygans.database.question.question_status.QuestionStatusService;
import com.cygans.database.question.question_status.StatusOfQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestGetQuestionStatusId {

    @Mock
    private QuestionStatusRepository questionStatusRepository;

    @InjectMocks
    private QuestionStatusService service;

    @Test
    public void testGetQuestionStatusIdWhenStatusExists() {
        StatusOfQuestion status = StatusOfQuestion.NO_ANSWER;
        QuestionStatus questionStatus = new QuestionStatus(status.getText());
        when(questionStatusRepository.findQuestionStatusByStatus(status.getText())).thenReturn(questionStatus);

        Long actualId = service.geQuestionStatusId(status);

        assertNull(actualId);
    }
}
