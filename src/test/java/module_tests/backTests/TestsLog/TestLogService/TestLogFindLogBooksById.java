package module_tests.backTests.TestsLog.TestLogService;

import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogRepository;
import com.cygans.database.log_book.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет метод findLogBooksById класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogFindLogBooksById {
    @Mock
    private LogRepository repository;

    @InjectMocks
    private LogService service;

    /**
     * Тест на успешное получение Log по идентификатору.
     */
    @Test
    public void testFindLogByIdSuccess() {
        Long logId = 1L;
        Log expectedLog = new Log();
        when(repository.findById(logId)).thenReturn(Optional.of(expectedLog));

        Log result = service.findLogBooksById(logId);

        assertEquals(expectedLog, result, "Возвращенный Log не соответствует ожидаемому Log");
        verify(repository, times(1)).findById(logId);
    }

    /**
     * Тест на поиск Log по идентификатору, когда Log с указанным ID отсутствует.
     */
    @Test
    public void testFindLogByIdNotFound() {
        Long logId = 1L;
        when(repository.findById(logId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.findLogBooksById(logId));
        verify(repository, times(1)).findById(logId);
    }
}


