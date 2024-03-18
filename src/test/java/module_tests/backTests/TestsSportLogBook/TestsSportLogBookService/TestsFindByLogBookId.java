package module_tests.backTests.TestsSportLogBook.TestsSportLogBookService;

import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookRepository;
import com.cygans.database.sport_log_book.SportLogBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsFindByLogBookId {

    @Mock
    private SportLogBookRepository repository;

    @InjectMocks
    private SportLogBookService service;

    @BeforeEach
    public void setUp() {
        service = new SportLogBookService(repository);
    }

    /**
     * Тест проверяет, что метод findByLogBookId вызывает соответствующий метод репозитория.
     */
    @Test
    public void testFindByLogBookId() {
        Long logBookId = 1L;
        SportLogBook expectedLogBook = new SportLogBook();
        when(repository.findByLogBookId(logBookId)).thenReturn(expectedLogBook);
        SportLogBook result = service.findByLogBookId(logBookId);
        assertEquals(expectedLogBook, result, "Вернулся неверный объект");
    }

}
