package backTests.TestsEmotionalLogBook.TestsEatingLogBookService;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookRepository;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestEmotionalLogBookFindByLogBookId {

    @Mock
    private EmotionalLogBookRepository repositoryMock;

    @InjectMocks
    private EmotionalLogBookService service;

    @BeforeEach
    void setUp() {
        service = new EmotionalLogBookService(repositoryMock);
    }

    @Test
    void testSaveEmotionalLogCallsRepositorySaveMethod() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        service.saveEmotionalLog(emotionalLogBook);

        verify(repositoryMock).save(emotionalLogBook);
    }

    @Test
    void testSaveEmotionalLogCallsRepositorySaveMethodOnce() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        service.saveEmotionalLog(emotionalLogBook);

        verify(repositoryMock).save(emotionalLogBook);
    }

    @Test
    void testSaveEmotionalLogCallsRepositorySaveMethodWithCorrectArgument() {
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        service.saveEmotionalLog(emotionalLogBook);

        verify(repositoryMock).save(emotionalLogBook);
    }
}
