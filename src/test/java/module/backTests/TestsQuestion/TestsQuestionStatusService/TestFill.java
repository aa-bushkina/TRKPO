package module.backTests.TestsQuestion.TestsQuestionStatusService;


import com.cygans.database.question.question_status.QuestionStatus;
import com.cygans.database.question.question_status.QuestionStatusRepository;
import com.cygans.database.question.question_status.QuestionStatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestFill {

    @Mock
    private QuestionStatusRepository questionStatusRepository;

    @InjectMocks
    private QuestionStatusService service;

    @Test
    public void testFillWhenRepositoryIsEmpty() {
        when(questionStatusRepository.count()).thenReturn(0L);
        service.fill();

        verify(questionStatusRepository, times(2)).save(Mockito.any(QuestionStatus.class));
    }

    @Test
    public void testFillWhenRepositoryHasTwoOrMoreEntries() {
        when(questionStatusRepository.count()).thenReturn(3L);
        service.fill();

        verify(questionStatusRepository, times(0)).save(Mockito.any(QuestionStatus.class));
    }
}
