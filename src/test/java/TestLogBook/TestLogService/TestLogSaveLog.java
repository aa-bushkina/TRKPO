package TestLogBook.TestLogService;

import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogRepository;
import com.cygans.database.log_book.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Тест проверяет метод fill класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogSaveLog {
    @Mock
    private LogRepository repository;

    @InjectMocks
    private LogService service;

    /**
     * Тест проверяет, что метод save вызывается с правильным объектом Log
     */
    @Test
    public void testSaveLog() {
        Log logToSave = new Log();
        service.saveLog(logToSave);

        verify(repository, times(1)).save(logToSave);
    }

    /**
     * Тест проверяет, что метод save вызывается только один раз
     */
    @Test
    public void testSaveLogSaveCalledOnce() {
        Log logToSave = new Log();
        service.saveLog(logToSave);

        verify(repository, times(1)).save(logToSave);
    }
}
