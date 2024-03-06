package module.backTests.TestsLog.TestLogsTypeService;

import com.cygans.database.log_book.logs_type.LogsType;
import com.cygans.database.log_book.logs_type.LogsTypeRepository;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет метод fill класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogTypeFill {

    @Mock
    private LogsTypeRepository logsTypeRepository;

    @InjectMocks
    private LogsTypeService logsTypeService;

    /**
     * Тест на успешное добавление записей при отсутствии записей в таблице
     */
    @Test
    public void testFillNoRecords() {
        when(logsTypeRepository.count()).thenReturn(0L);
        logsTypeService.fill();

        verify(logsTypeRepository, times(3)).save(any(LogsType.class));
    }

    /**
     * Тест на сообщение об ошибке при наличии более 3 записей в таблице
     */
    @Test
    public void testFillTooManyRecords() {
        when(logsTypeRepository.count()).thenReturn(4L);
        assertThrows(RuntimeException.class, () -> logsTypeService.fill());

        verify(logsTypeRepository, never()).save(any(LogsType.class));
    }

    /**
     * Тест на проверку, что метод count вызывается один раз
     */
    @Test
    public void testFillCountCalledOnce() {
        logsTypeService.fill();

        verify(logsTypeRepository, times(1)).count();
    }

    /**
     * Тест на сообщение об ошибке при наличии более 3 записей в таблице
     */
    @Test
    public void testFillTreeRecords() {
        when(logsTypeRepository.count()).thenReturn(3L);
        assertDoesNotThrow(() -> logsTypeService.fill());

        verify(logsTypeRepository, never()).save(any(LogsType.class));
    }
}

