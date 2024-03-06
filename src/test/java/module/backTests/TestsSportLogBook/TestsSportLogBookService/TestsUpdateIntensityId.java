package module.backTests.TestsSportLogBook.TestsSportLogBookService;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsUpdateIntensityId {

    @Mock
    private SportLogBookRepository repository;

    @InjectMocks
    private SportLogBookService service;

    @BeforeEach
    public void setUp() {
        service = new SportLogBookService(repository);
    }

    /**
     * Тест проверяет, что метод updateIntensityId вызывает findById и save в репозитории.
     */
    @Test
    public void testUpdateIntensityId() {
        Long id = 1L;
        Long intensityId = 2L;
        SportLogBook sportLogBook = new SportLogBook();
        when(repository.findById(id)).thenReturn(Optional.of(sportLogBook));
        service.updateIntensityId(id, intensityId);
        assertEquals(intensityId, sportLogBook.getIntensityId(), "Вернулся неверный id");
        verify(repository, times(1)).save(sportLogBook);
    }

}
