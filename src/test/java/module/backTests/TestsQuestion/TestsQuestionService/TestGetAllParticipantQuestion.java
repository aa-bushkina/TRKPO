package module.backTests.TestsQuestion.TestsQuestionService;


import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionRepository;
import com.cygans.database.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestGetAllParticipantQuestion {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService service;

    @Test
    public void testGetAllParticipantQuestionWhenQuestionsExist() {
        Long participantId = 1L;
        List<Question> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new Question());
        expectedQuestions.add(new Question());
        when(questionRepository.getQuestionByParticipantId(participantId)).thenReturn(expectedQuestions);

        List<Question> actualQuestions = service.getAllParticipantQuestion(participantId);

        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    public void testGetAllParticipantQuestionWhenNoQuestionsExist() {
        Long participantId = 2L;
        List<Question> expectedQuestions = new ArrayList<>();
        when(questionRepository.getQuestionByParticipantId(participantId)).thenReturn(expectedQuestions);

        List<Question> actualQuestions = service.getAllParticipantQuestion(participantId);

        assertEquals(expectedQuestions, actualQuestions);
    }
}
