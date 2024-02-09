package backTests.TestsEatingLogBook.TestsEatingLogBookService;

import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookRepository;
import com.cygans.database.eating_log_book.EatingLogBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEatingLogBookFindById {

    @Mock
    private EatingLogBookRepository repository;

    @InjectMocks
    private EatingLogBookService service;

    @Test
    public void testFindByLogBookIdFound() {
        Long logBookId = 1L;
        EatingLogBook expectedLogBook = new EatingLogBook();
        expectedLogBook.setLogBookId(logBookId);

        when(repository.findByLogBookId(logBookId)).thenReturn(expectedLogBook);

        EatingLogBook result = service.findByLogBookId(logBookId);

        assertEquals(expectedLogBook, result);
    }

    @Test
    public void testFindByLogBookIdNotFound() {
        Long logBookId = 1L;
        when(repository.findByLogBookId(logBookId)).thenReturn(null);
        EatingLogBook result = service.findByLogBookId(logBookId);

        assertNull(result);
    }
}
