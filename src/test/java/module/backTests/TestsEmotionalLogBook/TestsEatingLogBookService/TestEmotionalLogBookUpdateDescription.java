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

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmotionalLogBookUpdateDescription {

    @Mock
    private EmotionalLogBookRepository repositoryMock;

    @InjectMocks
    private EmotionalLogBookService service;

    @BeforeEach
    void setUp() {
        service = new EmotionalLogBookService(repositoryMock);
    }

    @Test
    void testUpdateEmotionalDescriptionCallsRepositoryFindByIdMethod() {
        Long id = 1L;
        String description = "Updated description";
        when(repositoryMock.findById(id)).thenReturn(Optional.of(new EmotionalLogBook()));
        service.updateEmotionalDescription(id, description);

        verify(repositoryMock).findById(id);
    }

    @Test
    void testUpdateEmotionalDescriptionCallsSetDescriptionMethodOnEmotionalLogBook() {
        Long id = 1L;
        String description = "Updated description";
        EmotionalLogBook emotionalLogBookMock = mock(EmotionalLogBook.class);
        when(repositoryMock.findById(id)).thenReturn(Optional.of(emotionalLogBookMock));
        service.updateEmotionalDescription(id, description);

        verify(emotionalLogBookMock).setDescription(description);
    }


    @Test
    void testUpdateEmotionalDescriptionCallsRepositorySaveMethod() {
        Long id = 1L;
        String description = "Updated description";
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(emotionalLogBook));
        service.updateEmotionalDescription(id, description);

        verify(repositoryMock).save(emotionalLogBook);
    }
}
