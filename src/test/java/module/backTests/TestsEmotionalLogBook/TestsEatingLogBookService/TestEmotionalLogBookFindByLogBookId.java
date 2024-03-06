package module.backTests.TestsEmotionalLogBook.TestsEatingLogBookService;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookRepository;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void testFindByLogBookId_ReturnsEmotionalLogBook_WhenFound() {
        Long logBookId = 1L;
        EmotionalLogBook expectedEmotionalLogBook = new EmotionalLogBook();
        when(repositoryMock.findByLogBookId(logBookId)).thenReturn(expectedEmotionalLogBook);
        EmotionalLogBook result = service.findByLogBookId(logBookId);

        assertEquals(expectedEmotionalLogBook, result);
    }

    @Test
    void testFindByLogBookId_ReturnsNull_WhenNotFound() {
        Long logBookId = 1L;
        when(repositoryMock.findByLogBookId(logBookId)).thenReturn(null);
        EmotionalLogBook result = service.findByLogBookId(logBookId);

        assertNull(result);
    }

    @Test
    void testFindByLogBookId_CallsRepositoryFindByLogBookIdMethod() {
        Long logBookId = 1L;
        service.findByLogBookId(logBookId);

        verify(repositoryMock).findByLogBookId(logBookId);
    }
}

