package backTests.TestsSportLogBook.TestsSportLogBookService;

import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookRepository;
import com.cygans.database.sport_log_book.SportLogBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsSaveSportLog {

    @Mock
    private SportLogBookRepository repository;

    @InjectMocks
    private SportLogBookService service;

    @BeforeEach
    public void setUp() {
        service = new SportLogBookService(repository);
    }

    /**
     * Тест проверяет, что метод saveSportLog вызывает save в репозитории с переданным объектом SportLogBook.
     */
    @Test
    public void testSaveSportLog() {
        SportLogBook sportLogBook = new SportLogBook();
        service.saveSportLog(sportLogBook);
        verify(repository, times(1)).save(sportLogBook);
    }

}
