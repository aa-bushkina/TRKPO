package TestsSportLogBook.TestsSportLogBookService;

import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookRepository;
import com.cygans.database.sport_log_book.SportLogBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsUpdateDuration {

    @Mock
    private SportLogBookRepository repository;

    @InjectMocks
    private SportLogBookService service;

    @BeforeEach
    public void setUp() {
        service = new SportLogBookService(repository);
    }

    /**
     * Тест проверяет, что метод updateDuration вызывает findById и save в репозитории.
     */
    @Test
    public void testUpdateDuration() {
        Long id = 1L;
        int duration = 60;
        SportLogBook sportLogBook = new SportLogBook();
        when(repository.findById(id)).thenReturn(Optional.of(sportLogBook));
        service.updateDuration(id, duration);
        assertEquals(duration, sportLogBook.getDuration(), "Вернулось неверное описание");
        verify(repository, times(1)).save(sportLogBook);
    }

}
