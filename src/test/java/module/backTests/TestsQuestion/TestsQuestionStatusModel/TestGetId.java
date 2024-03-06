package module.backTests.TestsQuestion.TestsQuestionStatusModel;

import com.cygans.database.question.question_status.QuestionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TestGetId {
    @Test
    public void testGetId() {
        QuestionStatus questionStatus = new QuestionStatus();
        assertNull(questionStatus.getId(), "getId() вернул неверный результат");
    }
}
