package module_tests.backTests.TestsEmotionalLogBook.TestsEatingLogBookService;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookRepository;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmotionalLogBookFindAll {

    @Mock
    private EmotionalLogBookRepository repositoryMock;

    @InjectMocks
    private EmotionalLogBookService service;

    @BeforeEach
    void setUp() {
        service = new EmotionalLogBookService(repositoryMock);
    }

    @Test
    void testFindAllReturnsEmptyListWhenRepositoryReturnsEmptyList() {
        when(repositoryMock.findAll()).thenReturn(new ArrayList<>());
        List<EmotionalLogBook> result = service.findAll();

        assertEquals(0, result.size());
    }

    @Test
    void testFindAllReturnsListWithOneItemWhenRepositoryReturnsOneItem() {
        List<EmotionalLogBook> emotionalLogBooks = new ArrayList<>();
        emotionalLogBooks.add(new EmotionalLogBook());
        when(repositoryMock.findAll()).thenReturn(emotionalLogBooks);
        List<EmotionalLogBook> result = service.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void testFindAllReturnsCorrectListWhenRepositoryReturnsMultipleItems() {
        List<EmotionalLogBook> emotionalLogBooks = new ArrayList<>();
        emotionalLogBooks.add(new EmotionalLogBook());
        emotionalLogBooks.add(new EmotionalLogBook());
        when(repositoryMock.findAll()).thenReturn(emotionalLogBooks);
        List<EmotionalLogBook> result = service.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindAllCallsRepositoryFindAllMethod() {
        service.findAll();

        verify(repositoryMock).findAll();
    }
}
